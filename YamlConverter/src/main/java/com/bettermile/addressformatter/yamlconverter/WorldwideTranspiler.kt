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

import com.bettermile.addressformatter.template.AddressTemplate
import com.bettermile.addressformatter.template.AddressTemplateDefinition
import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.buildCodeBlock

object WorldwideTranspiler {
    private val countryFormatType = ClassName("com.bettermile.addressformatter", "CountryFormat")
    private val countryFormatReplaceType = ClassName("com.bettermile.addressformatter", "CountryFormat", "Replace")
    private val mustacheClass = AddressTemplate::class

    fun yamlToFile(obj: ObjectNode): FileSpec {
        return generatedFileSpec(fileName = "Worldwide") {
            addType(
                TypeSpec.objectBuilder("Worldwide")
                    .addModifiers(KModifier.INTERNAL)
                    .apply {
                        val countryBlocks = mutableListOf<CodeBlock>()
                        val annotations = mutableListOf<AnnotationSpec>()
                        for ((key, value) in obj.properties()) {
                            if (key.startsWith("generic") || key.startsWith("fallback")) {
                                addProperty(
                                    PropertySpec.builder(key, mustacheClass, KModifier.PRIVATE)
                                        .addAnnotation(
                                            AnnotationSpec.builder(AddressTemplateDefinition::class)
                                                .addMember("%S", value.asText())
                                                .build()
                                        )
                                        .delegate("lazy(AddressTemplates::%L)", key)
                                        .build()
                                )
                            } else if (key == "default") {
                                addProperty(
                                    PropertySpec.builder(key, countryFormatType)
                                        .initializer(printCountryFormat(value as ObjectNode, key).first)
                                        .build()
                                )
                            } else {
                                countryBlocks += buildCodeBlock {
                                    beginControlFlow("%S to lazy", key)
                                    val countryFormat = printCountryFormat(value as ObjectNode, key)
                                    add(countryFormat.first)
                                    annotations += countryFormat.second
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
                            )
                                .addAnnotations(annotations)
                                .initializer(multilineFunctionCall("mapOf", countryBlocks)).build()
                        )
                    }.build()
            )
        }
    }

    private fun printCountryFormat(valueObject: ObjectNode, country: String): Pair<CodeBlock, List<AnnotationSpec>> {
        val annotations = mutableListOf<AnnotationSpec>()
        val parameters = buildMap {
            printTemplateProperty(valueObject, "address_template", country)?.also {
                put("addressTemplate", it.first)
                it.second?.also { annotations += it }
            }
            printTemplateProperty(valueObject, "fallback_template", country)?.also {
                put("fallbackTemplate", it.first)
                it.second?.also { annotations += it }
            }
            printRegexProperty(valueObject, "replace")?.also { put("replace", it) }
            printRegexProperty(valueObject, "postformat_replace")?.also { put("postformatReplace", it) }
            printProperty(valueObject, "use_country")?.also { put("useCountry", it) }
            printProperty(valueObject, "change_country")?.also { put("changeCountry", it) }
            printProperty(valueObject, "add_component")?.also { put("addComponent", it) }
        }.map { CodeBlock.of("${it.key}·=·%L", it.value) }
        return multilineFunctionCall(countryFormatType, parameters) to annotations
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
        propertyYamlName: String,
        country: String,
    ): Pair<CodeBlock, AnnotationSpec?>? {
        return if (valueObject.has(propertyYamlName)) {
            val template = valueObject[propertyYamlName].asText()
            when {
                template.startsWith("generic") || template.startsWith("fallback") ->
                    CodeBlock.of("%L", template) to null

                else -> {
                    val annotation = AnnotationSpec.builder(AddressTemplateDefinition::class)
                        .addMember("%S", template)
                        .addMember("\"%L_%L\"", country, propertyYamlName)
                        .build()
                    CodeBlock.of("AddressTemplates.%L_%L", country, propertyYamlName) to annotation
                }
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
