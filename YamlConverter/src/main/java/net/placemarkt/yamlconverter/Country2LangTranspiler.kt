package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.joinToCode

object Country2LangTranspiler {
    fun yamlToFile(node: ObjectNode): FileSpec {
        return generatedFileSpec("Country2Lang") {
            val elements: List<CodeBlock> = node.properties().map { (key, value) ->
                val languages: List<CodeBlock> = value.asText().split(',').map { CodeBlock.of("%S", it.uppercase()) }
                CodeBlock.of("%S to listOf(%L)", key, languages.joinToCode(", "))
            }
            addProperty(
                PropertySpec.builder(
                    name = "country2Languages",
                    type = Map::class.parameterizedBy(String::class)
                        .plusParameter(List::class.parameterizedBy(String::class)),
                    KModifier.INTERNAL,
                ).initializer(multilineFunctionCall("mapOf", elements))
                    .build()
            )
        }
    }
}
