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
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec

object ComponentsTranspiler {
    fun yamlToFile(array: List<ObjectNode>): FileSpec {
        return generatedFileSpec("Components") {
            val elements: List<CodeBlock> = array.flatMap { node ->
                buildList {
                    val name = node["name"].asText()
                    add(CodeBlock.of("%S♢to♢%S", name, name))
                    val aliases = node["aliases"]?.elements()?.asSequence().orEmpty()
                    addAll(aliases.map { CodeBlock.of("%S♢to♢%S", it.asText(), name) })
                }
            }
            addProperty(
                PropertySpec.builder(
                    name = "aliases",
                    type = Map::class.parameterizedBy(String::class, String::class),
                    KModifier.INTERNAL,
                ).initializer(multilineFunctionCall("mapOf", elements))
                    .build()
            )
        }
    }
}
