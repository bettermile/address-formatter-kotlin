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
        val worldwideObjectSpec = TypeSpec.objectBuilder("Worldwide")
            .addModifiers(KModifier.INTERNAL)
            .addProperties(worldwideTemplateProperties(obj))
            .build()
        return generatedFileSpec(fileName = "Worldwide") {
            addType(worldwideObjectSpec)
        }
    }

    private fun worldwideTemplateProperties(obj: ObjectNode): List<PropertySpec> {
        val properties = mutableListOf<PropertySpec>()
        val countryBlocks = mutableListOf<CodeBlock>()
        val countryAnnotations = mutableListOf<AnnotationSpec>()
        for ((key, value) in obj.properties()) {
            if (key.startsWith("generic") || key.startsWith("fallback")) {
                val templateAnnotation = addressTemplateDefinitionAnnotationSpec(value.asText())
                val templateProperty = PropertySpec.builder(key, mustacheClass, KModifier.PRIVATE)
                    .addAnnotation(templateAnnotation)
                    .delegate("lazy(AddressTemplates::%L)", key)
                    .build()
                properties += templateProperty
            } else if (key == "default") {
                val (initializer, defaultAnnotations) = countryFormatCodeAndAnnotations(key, value as ObjectNode)
                val countryInfoProperty = PropertySpec.builder(key, countryFormatType)
                    .initializer(initializer)
                    .addAnnotations(defaultAnnotations)
                    .build()
                properties += countryInfoProperty
            } else {
                val (countryFormat, annotations) = countryFormatCodeAndAnnotations(key, value as ObjectNode)
                countryAnnotations += annotations
                countryBlocks += buildCodeBlock {
                    beginControlFlow("%S to lazy", key)
                    add(countryFormat)
                    unindent()
                    add("\n}")
                }
            }
        }

        properties += PropertySpec.builder(
            name = "countries",
            type = Map::class.parameterizedBy(String::class)
                .plusParameter(Lazy::class.asClassName().parameterizedBy(countryFormatType))
        ).initializer(multilineFunctionCall("mapOf", countryBlocks))
            .addAnnotations(countryAnnotations)
            .build()
        return properties.toList()
    }

    private fun addressTemplateDefinitionAnnotationSpec(
        template: String,
        propertyName: String? = null,
    ): AnnotationSpec {
        return AnnotationSpec.builder(AddressTemplateDefinition::class)
            .addMember("\"\"\"\n%L\"\"\"", template)
            .apply { if (propertyName != null) addMember("propertyName = %S", propertyName) }
            .build()
    }

    private fun countryFormatCodeAndAnnotations(
        key: String,
        valueObject: ObjectNode,
    ): Pair<CodeBlock, List<AnnotationSpec>> {
        val annotations = mutableListOf<AnnotationSpec>()
        val parameters = buildMap {
            templateCodeBlockAndAnnotation(key, valueObject, "address_template")?.also { (codeBlock, annotation) ->
                put("addressTemplate", codeBlock)
                if (annotation != null) annotations.add(annotation)
            }
            templateCodeBlockAndAnnotation(key, valueObject, "fallback_template")?.also { (codeBlock, annotation) ->
                put("fallbackTemplate", codeBlock)
                if (annotation != null) annotations.add(annotation)
            }
            regexPropertyCodeBlock(valueObject, "replace")?.also { put("replace", it) }
            regexPropertyCodeBlock(valueObject, "postformat_replace")?.also { put("postformatReplace", it) }
            propertyCodeBlock(valueObject, "use_country")?.also { put("useCountry", it) }
            propertyCodeBlock(valueObject, "change_country")?.also { put("changeCountry", it) }
            propertyCodeBlock(valueObject, "add_component")?.also { put("addComponent", it) }
        }.map { CodeBlock.of("${it.key}·=·%L", it.value) }
        return multilineFunctionCall(countryFormatType, parameters) to annotations.toList()
    }

    private fun propertyCodeBlock(
        valueObject: ObjectNode,
        propertyYamlName: String
    ): CodeBlock? {
        return if (valueObject.has(propertyYamlName)) {
            CodeBlock.of("%S", valueObject[propertyYamlName].asText())
        } else {
            null
        }
    }

    private fun templateCodeBlockAndAnnotation(
        name: String,
        valueObject: ObjectNode,
        propertyYamlName: String,
    ): Pair<CodeBlock, AnnotationSpec?>? {
        return if (valueObject.has(propertyYamlName)) {
            val template = valueObject[propertyYamlName].asText()
            when {
                template.startsWith("generic") || template.startsWith("fallback") ->
                    CodeBlock.of("%L", template) to null

                else -> CodeBlock.of("AddressTemplates.%L_%L", name, propertyYamlName) to
                        addressTemplateDefinitionAnnotationSpec(template, "${name}_$propertyYamlName")
            }
        } else {
            null
        }
    }

    private fun regexPropertyCodeBlock(
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
