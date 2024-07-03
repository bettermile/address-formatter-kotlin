/*
 * Copyright 2022 GLS eComLab
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

package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.node.TextNode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.joinToCode

object RegionNamingTranspiler {

    private val nameType = ClassName("net.placemarkt", "Name")

    fun yamlToFile(fileName: String, propertyName: String, obj: ObjectNode): FileSpec {
        return generatedFileSpec(fileName) {
            val elements: List<CodeBlock> = obj.properties().map { (country, regionCodes) ->
                val regionBlocks: List<CodeBlock> = regionCodes.properties().map { (region, nameDefinition) ->
                    val nameParameters = buildMap<String, CodeBlock> {
                        if (nameDefinition is TextNode) {
                            put("default", CodeBlock.of("%S", nameDefinition.asText()))
                        } else {
                            put("default", CodeBlock.of("%S", nameDefinition["default"].asText()))
                            nameDefinition["alt"]?.also {
                                put("alternative", CodeBlock.of("%S", it.asText()))
                            }
                            val alternatives = nameDefinition.properties()
                                .filterNot { it.key in listOf("default", "alt") }
                                .map { (key, value) ->
                                    check(key.startsWith("alt_")) { "unexpected key: '$key'" }
                                    CodeBlock.of("%S·to·%S", key.drop(4), value.asText())
                                }
                            if (alternatives.isNotEmpty()) {
                                put("alternativesByLanguage", CodeBlock.of("mapOf(%L)", alternatives.joinToCode(", ")))
                            }
                        }
                    }.map { CodeBlock.of("${it.key}·=·%L", it.value) }
                    CodeBlock.of("%S to %T(%L)", region, nameType, nameParameters.joinToCode(separator = ", "))
                }
                buildCodeBlock {
                    add("%S to ", country)
                    add(multilineFunctionCall("mapOf", regionBlocks))
                }
            }
            addProperty(
                PropertySpec.builder(
                    name = propertyName,
                    type = Map::class.parameterizedBy(String::class)
                        .plusParameter(Map::class.parameterizedBy(String::class).plusParameter(nameType)),
                    KModifier.INTERNAL,
                ).initializer(multilineFunctionCall("mapOf", elements))
                    .build()
            )
        }
    }
}
