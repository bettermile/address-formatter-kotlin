package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.joinToCode

object WorldwideTranspiler {
    private val countryFormatType = ClassName("net.placemarkt", "CountryFormat")
    private val countryFormatReplaceType = ClassName("net.placemarkt", "CountryFormat", "Replace")

    fun yamlToFile(obj: ObjectNode): FileSpec {
        return generatedFileSpec(fileName = "Worldwide") {
            addType(
                TypeSpec.objectBuilder("Workldwide")
                    .addModifiers(KModifier.INTERNAL)
                    .apply {
                        val countryBlocks = mutableListOf<CodeBlock>()
                        for ((key, value) in obj.properties()) {
                            if (key.startsWith("generic") || key.startsWith("fallback")) {
                                addProperty(
                                    PropertySpec.builder(key, String::class, KModifier.PRIVATE, KModifier.CONST)
                                        .initializer(CodeBlock.of("%P", value.asText()))
                                        .build()
                                )
                            } else if (key == "default") {
                                addProperty(
                                    PropertySpec.builder(key, countryFormatType)
                                        .initializer(printCountryFormat(value as ObjectNode))
                                        .build()
                                )
                            } else {
                                countryBlocks += buildCodeBlock {
                                    beginControlFlow("%S to lazy", key)
                                    add(printCountryFormat(value as ObjectNode))
                                    unindent()
                                    add("}")
                                }
                            }
                        }

                        addProperty(
                            PropertySpec.builder(
                                name = "countries",
                                type = Map::class.parameterizedBy(String::class)
                                    .plusParameter(Lazy::class.asClassName().parameterizedBy(countryFormatType))
                            ).initializer(
                                CodeBlock.of(
                                    format = "mapOf(%L)",
                                    countryBlocks.joinToCode(separator = ",\n", prefix = "\n", suffix = ",\n")
                                )
                            ).build()
                        )
                    }.build()
            ).build()
        }
    }

    private fun printCountryFormat(valueObject: ObjectNode): CodeBlock {
        val parameters = buildMap {
            printProperty(valueObject, "address_template")?.also { put("addressTemplate", it) }
            printProperty(valueObject, "fallback_template")?.also { put("fallbackTemplate", it) }
            printRegexProperty(valueObject, "replace")?.also { put("replace", it) }
            printRegexProperty(valueObject, "postformat_replace")?.also { put("postformatReplace", it) }
            printProperty(valueObject, "use_country")?.also { put("useCountry", it) }
            printProperty(valueObject, "change_country")?.also { put("changeCountry", it) }
            printProperty(valueObject, "add_component")?.also { put("addComponent", it) }
        }.map { CodeBlock.of("${it.key}·=·%L", it.value) }
        return buildCodeBlock {
            add("%T(", countryFormatType)
            indent()
            add("%L", parameters.joinToCode(separator = ",\n", prefix = "\n", suffix = ",\n"))
            unindent()
            add(")\n")
        }
    }

    private fun printProperty(
        valueObject: ObjectNode,
        propertyYamlName: String
    ): CodeBlock? {
        return if (valueObject.has(propertyYamlName)) {
            val template = valueObject[propertyYamlName].asText()
            when {
                template.startsWith("generic") || template.startsWith("fallback") -> CodeBlock.of("%L", template)
                else -> CodeBlock.of("%S", template)
            }
        } else {
            null
        }
    }

    private fun printRegexProperty(
        valueObject: ObjectNode,
        propertyYamlName: String
    ): CodeBlock? {
        return if (valueObject.has(propertyYamlName)) {
            val elements = valueObject[propertyYamlName].elements().asSequence().map { jsonNode ->
                val regex = jsonNode[0].asText()
                val replacement = jsonNode[1].asText()
                buildCodeBlock {
                    add("%T(", countryFormatReplaceType)
                    indent()
                    add("search·=·%S, replacement·=·%S", regex, replacement)
                    unindent()
                    add(")")
                }
            }
            buildCodeBlock {
                indent()
                add("listOf(%L", elements.toList().joinToCode(separator = ",\n", prefix = "\n", suffix = ",\n"))
                unindent()
                add(")")
            }
        } else {
            null
        }
    }
}