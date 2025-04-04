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
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.joinToCode
import java.nio.file.Path
import kotlin.io.path.name
import kotlin.io.path.nameWithoutExtension

object TestCasesTranspiler {
    private val testAnnotationClass = ClassName("kotlin.test", "Test")
    private val assertEqualsFunctionName = ClassName("kotlin.test", "assertEquals")
    private val addressFormatterClass = ClassName("com.bettermile.addressformatter", "AddressFormatter")

    fun yamlToFile(
        path: Path,
        tests: List<ObjectNode>,
    ): FileSpec {
        val className = path.nameWithoutExtension.uppercase()
        val parentFolder = path.parent.name
        // some descriptions are duplicated, so we need add a suffix to have different test function names
        val functionNameCounter = mutableMapOf<String, Int>().withDefault { 0 }
        return generatedFileSpec(className, packageName = "$DEFAULT_PACKAGE.$parentFolder") {
            val classSpec = TypeSpec.classBuilder(className).apply {
                val isAbbreviate = parentFolder == "abbreviations"
                addProperty(
                    PropertySpec.builder("addressFormatter", addressFormatterClass, KModifier.PRIVATE).apply {
                        initializer(
                            CodeBlock.of(
                                "%T(abbreviate = %L, appendCountry = false)",
                                addressFormatterClass,
                                isAbbreviate,
                            )
                        )
                    }.build()
                )
                tests.forEach { testCase ->
                    val components: List<CodeBlock> = testCase["components"].properties().map { (key, value) ->
                        CodeBlock.of("%S to %S", key, value.asText())
                    }
                    val expected = testCase["expected"].asText()
                    val originalDescription = testCase["description"]?.asText()
                    val description = originalDescription ?: expected.trim().replace("\n", ", ")
                    val functionName = description.replace("[^\\w_]+".toRegex(), "_")
                    val occurrences = functionNameCounter.merge(functionName, 1, Int::plus)!!
                    val realFunctionName = if (occurrences == 1) functionName else "${functionName}_$occurrences"
                    addFunction(
                        FunSpec.builder(realFunctionName).apply {
                            addAnnotation(testAnnotationClass)
                            if (originalDescription != null) addComment("description: %L", description)
                            addStatement("val components = mapOf(%L)", components.joinToCode(",♢"))
                            addStatement("val expected = %S", expected)
                            addStatement("val actual = addressFormatter.format(components = components)")
                            addStatement("%T(expected, actual)", assertEqualsFunctionName)
                        }.build()
                    )
                }
            }.build()
            addType(classSpec)
        }
    }
}
