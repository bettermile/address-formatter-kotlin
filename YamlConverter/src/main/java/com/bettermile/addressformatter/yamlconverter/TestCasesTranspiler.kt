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
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.joinToCode

object TestCasesTranspiler {
    private val testCaseClass = ClassName("com.bettermile.addressformatter", "TestCase")

    fun yamlToFile(testCases: List<Pair<String, List<ObjectNode>>>): FileSpec {
        return generatedFileSpec("TestCases") {
            val type = List::class.asClassName().parameterizedBy(testCaseClass)
            val elements: List<CodeBlock> = testCases.flatMap { (fileName, testCases) ->
                testCases.map { testCase ->
                    val components: List<CodeBlock> = testCase["components"].properties().map { (key, value) ->
                        CodeBlock.of("%S·to·%S", key, value.asText())
                    }
                    val properties = listOf(
                        CodeBlock.of("components·=·mapOf(%L)", components.joinToCode(", ")),
                        CodeBlock.of("expected·=·%S", testCase["expected"].asText()),
                        CodeBlock.of("description·=·%S", testCase["description"].asText()),
                        CodeBlock.of("fileName·=·%S", fileName),
                    )
                    multilineFunctionCall(testCaseClass, properties)
                }
            }
            addProperty(
                PropertySpec.builder("testCases", type = type)
                    .initializer(multilineFunctionCall("listOf", elements))
                    .build()
            )
        }
    }
}
