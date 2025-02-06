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

import kotlin.test.Test
import kotlin.test.assertEquals

class AddressFormattersRendererTest {

    @Test
    fun `should render literal char`() {
        val input = listOf(AddressTemplateStructure.Simple.Literal("s"))
        val expected = """
            return buildString {
              append('s')
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render literal string`() {
        val input = listOf(AddressTemplateStructure.Simple.Literal("some text"))
        val expected = """
            return buildString {
              append("some text")
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render literal newline char`() {
        val input = listOf(AddressTemplateStructure.Simple.Literal("\n"))
        val expected = """
            return buildString {
              append('\n')
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render literal newline string`() {
        val input = listOf(AddressTemplateStructure.Simple.Literal("some text\nwith newline"))
        val expected = """
            return buildString {
              append("some text\nwith newline")
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render placeholder component`() {
        val input = listOf(AddressTemplateStructure.Simple.Placeholder("component"))
        val expected = """
            return buildString {
              context["component"]?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render one literal lambda`() {
        val input = listOf(
            AddressTemplateStructure.FirstLambda(listOf(listOf(AddressTemplateStructure.Simple.Literal("text"))))
        )
        val expected = """
            return buildString {
              sequence<String> {
                yield(
                  buildString {
                    append("text")
                  }
                )
              }
              .firstOrNull(String::isNotBlank)?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render one placeholder lambda`() {
        val input = listOf(
            AddressTemplateStructure.FirstLambda(listOf(listOf(AddressTemplateStructure.Simple.Placeholder("text"))))
        )
        val expected = """
            return buildString {
              sequence<String> {
                yield(
                  buildString {
                    context["text"]?.also(::append)
                  }
                )
              }
              .firstOrNull(String::isNotBlank)?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render literal and placeholder lambda`() {
        val input = listOf(
            AddressTemplateStructure.FirstLambda(
                listOf(
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("text"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    )
                )
            )
        )
        val expected = """
            return buildString {
              sequence<String> {
                yield(
                  buildString {
                    append(' ')
                    context["text"]?.also(::append)
                    append(' ')
                  }
                )
              }
              .firstOrNull(String::isNotBlank)?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render multiple elements lambda`() {
        val input = listOf(
            AddressTemplateStructure.FirstLambda(
                listOf(
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("text"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                    listOf(
                        AddressTemplateStructure.Simple.Placeholder("component"),
                        AddressTemplateStructure.Simple.Literal(", "),
                        AddressTemplateStructure.Simple.Placeholder("element"),
                    ),
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("street"),
                    ),
                )
            )
        )
        val expected = """
            return buildString {
              sequence<String> {
                yield(
                  buildString {
                    append(' ')
                    context["text"]?.also(::append)
                    append(' ')
                  }
                )
                yield(
                  buildString {
                    context["component"]?.also(::append)
                    append(", ")
                    context["element"]?.also(::append)
                  }
                )
                yield(
                  buildString {
                    append(' ')
                    context["street"]?.also(::append)
                  }
                )
              }
              .firstOrNull(String::isNotBlank)?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `should render complex example`() {
        val input = listOf(
            AddressTemplateStructure.Simple.Placeholder("attention"),
            AddressTemplateStructure.Simple.Literal("\n"),
            AddressTemplateStructure.Simple.Placeholder("house"),
            AddressTemplateStructure.Simple.Literal("\n"),
            AddressTemplateStructure.FirstLambda(
                listOf(
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("road"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("place"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("hamlet"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                )
            ),
            AddressTemplateStructure.Simple.Literal(" "),
            AddressTemplateStructure.Simple.Placeholder("house_number"),
            AddressTemplateStructure.Simple.Literal("\n"),
            AddressTemplateStructure.Simple.Placeholder("postcode"),
        )
        val expected = """
            return buildString {
              context["attention"]?.also(::append)
              append('\n')
              context["house"]?.also(::append)
              append('\n')
              sequence<String> {
                yield(
                  buildString {
                    append(' ')
                    context["road"]?.also(::append)
                    append(' ')
                  }
                )
                yield(
                  buildString {
                    append(' ')
                    context["place"]?.also(::append)
                    append(' ')
                  }
                )
                yield(
                  buildString {
                    append(' ')
                    context["hamlet"]?.also(::append)
                    append(' ')
                  }
                )
              }
              .firstOrNull(String::isNotBlank)?.also(::append)
              append(' ')
              context["house_number"]?.also(::append)
              append('\n')
              context["postcode"]?.also(::append)
            }

            """.trimIndent()

        val actual = AddressFormattersRenderer.render(input).toString()

        assertEquals(expected, actual)
    }
}
