/*
 * Copyright 2022 GLS eCom Lab GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bettermile.addressformatter.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.buildCodeBlock

object WorldwideTranspiler {
    private val countryFormatType = ClassName("com.bettermile.addressformatter", "CountryFormat")
    private val countryFormatReplaceType = ClassName("com.bettermile.addressformatter", "CountryFormat", "Replace")
    private val mustacheClass = ClassName("com.bettermile.addressformatter.mustache", "Mustache")

    fun yamlToFile(obj: ObjectNode): FileSpec {
        return generatedFileSpec(fileName = "Worldwide") {
            addType(
                TypeSpec.objectBuilder("Worldwide")
                    .addModifiers(KModifier.INTERNAL)
                    .apply {
                        val countryBlocks = mutableListOf<CodeBlock>()
                        for ((key, value) in obj.properties()) {
                            if (key.startsWith("generic") || key.startsWith("fallback")) {
                                addProperty(
                                    PropertySpec.builder(key, mustacheClass, KModifier.PRIVATE)
                                        .delegate(compileTemplate(value.asText()))
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
                                    add("\n}")
                                }
                            }
                        }

                        addProperty(
                            PropertySpec.builder(
                                name = "countries",
                                type = Map::class.parameterizedBy(String::class)
                                    .plusParameter(Lazy::class.asClassName().parameterizedBy(countryFormatType))
                            ).initializer(multilineFunctionCall("mapOf", countryBlocks)).build()
                        )
                    }.build()
            )
        }
    }

    private fun compileTemplate(template: String): CodeBlock {
        return buildCodeBlock {
            beginControlFlow("lazy")
            add("%L", template.toMustacheAnonymousClass())
            unindent()
            add("\n}")
        }
    }

    private fun String.toMustacheAnonymousClass(): TypeSpec = TypeSpec.anonymousClassBuilder()
        .addSuperinterface(mustacheClass)
        .addFunction(
            FunSpec.builder("execute")
                .addModifiers(KModifier.OVERRIDE)
                .addParameter("context", Map::class.parameterizedBy(String::class, String::class))
                .returns(String::class)
                .addCode(
                    buildCodeBlock {
                        beginControlFlow("return buildString")
                        add(generateMustacheCodeBlock())
                        endControlFlow()
                    }
                )
                .build()
        )
        .build()

    private fun String.generateMustacheCodeBlock(): CodeBlock = buildCodeBlock {
        var index = 0
        var literal = ""
        fun readName(): String {
            while (get(index).isWhitespace()) index++
            val endIndex = indexOf('}', index)
            return substring(index, endIndex).trim().also {
                index = endIndex
                while (get(index) == '}') index++
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
        while (index < length) {
            val char = get(index++)
            if (char != '{') {
                literal += char
            } else {
                appendLiteral()
                require(get(index++) == '{')
                val mustacheClassChar = get(index++)
                if (mustacheClassChar == '{') {
                    addStatement("context[%S]?.also(::append)", readName())
                } else {
                    require(mustacheClassChar == '#')
                    require(readName() == "first")
                    beginControlFlow("sequence")
                    do {
                        when (val firstBlockChar = get(index++)) {
                            '{' -> {
                                require(get(index++) == '{')
                                val mustacheClassChar2 = get(index++)
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
                                require(get(index++) == '|')
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

    private fun printCountryFormat(valueObject: ObjectNode): CodeBlock {
        val parameters = buildMap {
            printTemplateProperty(valueObject, "address_template")?.also { put("addressTemplate", it) }
            printTemplateProperty(valueObject, "fallback_template")?.also { put("fallbackTemplate", it) }
            printRegexProperty(valueObject, "replace")?.also { put("replace", it) }
            printRegexProperty(valueObject, "postformat_replace")?.also { put("postformatReplace", it) }
            printProperty(valueObject, "use_country")?.also { put("useCountry", it) }
            printProperty(valueObject, "change_country")?.also { put("changeCountry", it) }
            printProperty(valueObject, "add_component")?.also { put("addComponent", it) }
        }.map { CodeBlock.of("${it.key}·=·%L", it.value) }
        return multilineFunctionCall(countryFormatType, parameters)
    }

    private fun printProperty(
        valueObject: ObjectNode,
        propertyYamlName: String
    ): CodeBlock? {
        return if (valueObject.has(propertyYamlName)) {
            CodeBlock.of("%S", valueObject[propertyYamlName].asText())
        } else {
            null
        }
    }

    private fun printTemplateProperty(
        valueObject: ObjectNode,
        propertyYamlName: String
    ): CodeBlock? {
        return if (valueObject.has(propertyYamlName)) {
            val template = valueObject[propertyYamlName].asText()
            when {
                template.startsWith("generic") || template.startsWith("fallback") -> CodeBlock.of("%L", template)
                else -> CodeBlock.of("%L", template.toMustacheAnonymousClass())
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
            multilineFunctionCall("listOf", elements.toList())
        } else {
            null
        }
    }
}
