package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.joinToCode

object CountryNamesTranspiler {
    fun yamlToFile(node: ObjectNode): FileSpec {
        return generatedFileSpec("CountryNames") {
            val elements: List<CodeBlock> = node.properties().map { (key, value) ->
                CodeBlock.of("%S to %S", key, value.asText())
            }
            addProperty(
                PropertySpec.builder(
                    name = "countryNames",
                    type = Map::class.parameterizedBy(String::class, String::class),
                    KModifier.INTERNAL,
                ).initializer("mapOf(%L)", elements.joinToCode(separator = ",\n", prefix = "\n", suffix = ",\n"))
                    .build()
            )
        }
    }
}
