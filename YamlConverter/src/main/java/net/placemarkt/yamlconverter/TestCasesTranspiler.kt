package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.asClassName
import com.squareup.kotlinpoet.joinToCode

object TestCasesTranspiler {
    private val testCaseClass = ClassName("net.placemarkt", "TestCase")

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
