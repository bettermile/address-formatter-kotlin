package net.placemarkt.yamlconverter

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
                    add(CodeBlock.of("%S to %S", name, name))
                    val aliases = node["aliases"]?.elements()?.asSequence().orEmpty()
                    addAll(aliases.map { CodeBlock.of("%S to %S", it.asText(), name) })
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
