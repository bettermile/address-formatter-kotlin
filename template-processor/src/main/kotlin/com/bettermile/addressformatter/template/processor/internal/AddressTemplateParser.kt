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

package com.bettermile.addressformatter.template.processor.internal

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode

internal class AddressTemplateParser(
    private val logger: KSPLogger,
    private val node: KSNode,
    private val template: String,
) {
    private val elements = mutableListOf<AddressTemplateStructure>()
    private var index = 0
    private var literal = ""
    private var line = 0
    private var lastLineIndex = 0
    private val position: String
        get() = "line $line position ${index - lastLineIndex}"

    private fun reset() {
        elements.clear()
        index = 0
        literal = ""
        line = 0
        lastLineIndex = 0
    }

    internal fun parse(): List<AddressTemplateStructure>? {
        reset()
        while (index < template.length) {
            if (template.startsWith("{{", index)) {
                index += 2
                appendLiteral()
                if (!parsePlaceholder()) return null
            } else {
                val char = template[index++]
                if (char == '\n') {
                    line++
                    lastLineIndex = index
                }
                literal += char
            }
        }
        appendLiteral()
        return elements
    }

    private fun parsePlaceholder(): Boolean {
        if (index >= template.length) {
            logger.error("expected placeholder, templated ended", node)
            return false
        }
        return when (val mustacheClassChar = template[index++]) {
            '{' -> {
                val component = readComponentName(PLACEHOLDER_BRACKETS_COUNT)
                if (component != null) {
                    elements += AddressTemplateStructure.Simple.Placeholder(component)
                    true
                } else {
                    false
                }
            }

            '#' -> {
                when (val mustacheSequenceName = readComponentName(LAMBDA_BRACKETS_COUNT)) {
                    null -> false
                    "first" -> {
                        val lambdaPlaceholder = parseLambdaPlaceholder()
                        if (lambdaPlaceholder != null) {
                            elements += lambdaPlaceholder
                            true
                        } else {
                            false
                        }
                    }

                    else -> {
                        logger.error(
                            message = "expected 'first' lambda placeholder but found '$mustacheSequenceName' " +
                                    "at $position",
                            symbol = node,
                        )
                        false
                    }
                }
            }

            else -> {
                logger.error(
                    message = "cannot handle placeholder type char '$mustacheClassChar' at $position",
                    symbol = node,
                )
                false
            }
        }
    }

    private fun parseLambdaPlaceholder(): AddressTemplateStructure.FirstLambda? {
        val content = mutableListOf(mutableListOf<AddressTemplateStructure.Simple>())
        do {
            when {
                index > template.lastIndex - 2 -> {
                    logger.error("expected 'first' lambda placeholder end tag but template ended", node)
                    return null
                }

                template.startsWith("{{", index) -> {
                    index += 2
                    if (literal.isNotEmpty()) {
                        content.last() += AddressTemplateStructure.Simple.Literal(literal)
                        literal = ""
                    }
                    return when (content.parsePlaceholderInLambda()) {
                        true -> AddressTemplateStructure.FirstLambda(content.toList())
                        null -> null
                        false -> continue
                    }
                }

                template.startsWith("||", index) -> {
                    index += 2
                    if (literal.isNotEmpty()) {
                        content.last() += AddressTemplateStructure.Simple.Literal(literal)
                        literal = ""
                    }
                    content += mutableListOf<AddressTemplateStructure.Simple>()
                }

                else -> literal += template[index++]
            }
        } while (true)
    }

    /**
     * returns null -> error
     * returns true -> lambda parsing done
     * returns false -> continue lambda parsing
     */
    private fun MutableList<MutableList<AddressTemplateStructure.Simple>>.parsePlaceholderInLambda(): Boolean? {
        return when (val mustacheClassChar = template[index++]) {
            '{' -> {
                readComponentName(PLACEHOLDER_BRACKETS_COUNT)?.let { component ->
                    last() += AddressTemplateStructure.Simple.Placeholder(component)
                    false
                }
            }

            '/' -> {
                when (val lambdaName = readComponentName(LAMBDA_BRACKETS_COUNT)) {
                    "first" -> true
                    null -> null
                    else -> {
                        logger.error(
                            message = "expected 'first' lambda placeholder but found '$lambdaName' at $position",
                            symbol = node,
                        )
                        null
                    }
                }
            }

            else -> {
                logger.error(
                    message = "cannot handle placeholder type char '$mustacheClassChar' at $position",
                    symbol = node,
                )
                null
            }
        }
    }

    private fun appendLiteral() {
        if (literal.isNotEmpty()) {
            elements += AddressTemplateStructure.Simple.Literal(literal)
            literal = ""
        }
    }

    private fun readComponentName(expectedBracesCount: Int): String? {
        val expectedPlaceholderSuffix = "}".repeat(expectedBracesCount)
        val endIndex = template.indexOf(expectedPlaceholderSuffix, index)
        return if (endIndex != -1) {
            val name = template.substring(index, endIndex).trim()
            if (name.isNotEmpty()) {
                index = endIndex + expectedBracesCount
                name
            } else {
                logger.error(
                    message = "Expected non empty placeholder at $position",
                    symbol = node,
                )
                null
            }
        } else {
            logger.error(
                message = "Expected placeholder closing brackets '$expectedPlaceholderSuffix' at $position",
                symbol = node,
            )
            null
        }
    }
}

private const val LAMBDA_BRACKETS_COUNT = 2
private const val PLACEHOLDER_BRACKETS_COUNT = 3
