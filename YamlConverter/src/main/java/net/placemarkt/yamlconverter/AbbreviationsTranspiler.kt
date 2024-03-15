package net.placemarkt.yamlconverter

import com.fasterxml.jackson.databind.node.ObjectNode
import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.buildCodeBlock

object AbbreviationsTranspiler {
    fun yamlToFile(abbreviations: Map<String, ObjectNode>): FileSpec {
        return generatedFileSpec("Abbreviations") {
            val elements: List<CodeBlock> = abbreviations.map { (language, config) ->
                val languageComponents: List<CodeBlock> = config.properties().map { (component, abbreviations) ->
                    val abbreviationsCode: List<CodeBlock> =
                        abbreviations.properties().map { (longName, abbreviation) ->
                            CodeBlock.of("%S to %S", longName, abbreviation.asText())
                        }
                    buildCodeBlock {
                        add("%S to ", component)
                        add(multilineFunctionCall("mapOf", abbreviationsCode))
                    }
                }
                buildCodeBlock {
                    add("%S to ", language)
                    add(multilineFunctionCall("mapOf", languageComponents))
                }
            }
            addProperty(
                PropertySpec.builder(
                    name = "abbreviations",
                    type = Map::class.parameterizedBy(String::class)
                        .plusParameter(
                            Map::class.parameterizedBy(String::class)
                                .plusParameter(Map::class.parameterizedBy(String::class, String::class))
                        ),
                    KModifier.INTERNAL,
                ).initializer(multilineFunctionCall("mapOf", elements))
                    .build()
            )
        }
    }

}
