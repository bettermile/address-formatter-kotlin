package com.bettermile.addressformatter.template.processor

import com.bettermile.addressformatter.template.AddressTemplate
import com.bettermile.addressformatter.template.AddressTemplateDefinition
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSDeclaration
import com.google.devtools.ksp.validate
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.ksp.writeTo

class AddressTemplateProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator
) : SymbolProcessor {
    override fun process(resolver: Resolver): List<KSAnnotated> {
        val sequenceOfAnnotated =
            resolver.getSymbolsWithAnnotation(checkNotNull(AddressTemplateDefinition::class.qualifiedName))
                .filter(KSAnnotated::validate)
        val packages = sequenceOfAnnotated.flatMap { property ->
            val packageName = property.containingFile?.packageName?.asString()
                ?: kotlin.run { logger.error("no package found"); return@flatMap emptySequence() }
            val name = (property as? KSDeclaration)?.simpleName?.getShortName()
            @OptIn(KspExperimental::class)
            property.getAnnotationsByType(AddressTemplateDefinition::class).mapNotNull { addressTemplateDefinition ->
                val template = addressTemplateDefinition.value
                val propertyName = addressTemplateDefinition.propertyName.takeIf(String::isNotEmpty)
                    ?: name
                    ?: kotlin.run { logger.error("no property name found"); return@mapNotNull null }
                ToProcess(name = propertyName, packageName = packageName, template = template)
            }
        }.groupBy(ToProcess::packageName)
        packages.forEach { (_, toProcess) ->
            val fileSpec: FileSpec = toProcess.generateFile()
            // TODO check for other parameters
            fileSpec.writeTo(codeGenerator, Dependencies(true))
        }

        return emptyList()
    }
}

private data class ToProcess(
    val name: String,
    val packageName: String,
    val template: String,
)

private fun List<ToProcess>.generateFile(objectName: String = "AddressTemplates"): FileSpec {
    return FileSpec.builder(packageName = first().packageName, fileName = objectName)
        .addType(
            TypeSpec.objectBuilder(objectName)
                .apply {
                    map {
                        PropertySpec.builder(it.name, AddressTemplate::class).getter(
                            FunSpec.getterBuilder()
                                .addStatement("return %L", generateAddressTemplateTypeSpec(it.template))
                                .build()
                        ).build()
                    }.forEach(::addProperty)
                }
                .build()
        )
        .build()
}

fun generateAddressTemplateTypeSpec(template: String): TypeSpec = TypeSpec.anonymousClassBuilder()
    .addSuperinterface(AddressTemplate::class)
    .addFunction(
        FunSpec.builder("execute")
            .addModifiers(KModifier.OVERRIDE)
            .addParameter("context", Map::class.parameterizedBy(String::class, String::class))
            .returns(String::class)
            .addCode(
                buildCodeBlock {
                    beginControlFlow("return buildString")
                    add(generateMustacheCodeBlock(template))
                    endControlFlow()
                }
            )
            .build()
    )
    .build()

private fun generateMustacheCodeBlock(template: String): CodeBlock = buildCodeBlock {
    var index = 0
    var literal = ""
    fun readName(): String {
        while (template[index].isWhitespace()) index++
        val endIndex = template.indexOf('}', index)
        return template.substring(index, endIndex).trim().also {
            index = endIndex
            while (template[index] == '}') index++
        }
    }

    fun appendLiteral() {
        if (literal.isNotEmpty()) {
            if (literal.length == 1) {
                if (literal == "\n") literal = "\\n"
                addStatement("append('%L')", literal)
            } else {
                addStatement("append(\"%L\")", literal.replace("\n", "\\n"))
            }
            literal = ""
        }
    }
    while (index < template.length) {
        val char = template.get(index++)
        if (char != '{') {
            literal += char
        } else {
            appendLiteral()
            require(template.get(index++) == '{')
            val mustacheClassChar = template.get(index++)
            if (mustacheClassChar == '{') {
                addStatement("context[%S]?.also(::append)", readName())
            } else {
                require(mustacheClassChar == '#')
                require(readName() == "first")
                beginControlFlow("sequence")
                do {
                    when (val firstBlockChar = template.get(index++)) {
                        '{' -> {
                            require(template.get(index++) == '{')
                            val mustacheClassChar2 = template.get(index++)
                            if (mustacheClassChar2 == '{') {
                                literal += "\${context[\"${readName()}\"] ?: \"\"}"
                            } else {
                                require(mustacheClassChar2 == '/')
                                require(readName() == "first")
                                addStatement("yield(%P)", literal)
                                literal = ""
                                break
                            }
                        }

                        '|' -> {
                            require(template.get(index++) == '|')
                            addStatement("yield(%P)", literal)
                            literal = ""
                        }

                        else -> {
                            literal += firstBlockChar
                        }
                    }
                } while (true)
                endControlFlow()
                addStatement(".firstOrNull(String::isNotBlank)?.also(::append)")
            }
        }
    }
}
