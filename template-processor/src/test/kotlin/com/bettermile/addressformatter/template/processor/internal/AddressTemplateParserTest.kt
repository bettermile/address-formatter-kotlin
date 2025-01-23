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

import com.bettermile.addressformatter.template.processor.util.FakeKSNode
import com.bettermile.addressformatter.template.processor.util.FakeKSPLogger
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AddressTemplateParserTest {

    @Test
    fun `should parse literal`() {
        val logger = FakeKSPLogger()
        val expected = listOf(AddressTemplateStructure.Simple.Literal("text"))

        val actual = AddressTemplateParser(logger, FakeKSNode(), "text").parse()

        assertEquals(expected, actual)
        assertContentEquals(emptyList(), logger.errors)
    }

    @Test
    fun `should parse placeholder`() {
        val logger = FakeKSPLogger()
        val expected = listOf(AddressTemplateStructure.Simple.Placeholder("text"))

        val actual = AddressTemplateParser(logger, FakeKSNode(), "{{{text}}}").parse()

        assertEquals(expected, actual)
        assertContentEquals(emptyList(), logger.errors)
    }

    @Test
    fun `should parse lambda`() {
        val logger = FakeKSPLogger()
        val expected = listOf(
            AddressTemplateStructure.FirstLambda(
                listOf(
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("road"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                    listOf(
                        AddressTemplateStructure.Simple.Literal(" "),
                        AddressTemplateStructure.Simple.Placeholder("street"),
                        AddressTemplateStructure.Simple.Literal(" "),
                    ),
                )
            )
        )

        val template = "{{#first}} {{{road}}} || {{{street}}} {{/first}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertEquals(expected, actual)
        assertContentEquals(emptyList(), logger.errors)
    }

    @Test
    fun `should emit error on missing closing placeholder brackets`() {
        val logger = FakeKSPLogger()

        val template = "{{{road}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(listOf("Expected placeholder closing brackets '}}}' at line 0 position 3"), logger.errors)
    }

    @Test
    fun `should emit error on empty placeholder`() {
        val logger = FakeKSPLogger()

        val template = "{{{}}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(listOf("Expected non empty placeholder at line 0 position 3"), logger.errors)
    }

    @Test
    fun `should emit error on whitespace placeholder`() {
        val logger = FakeKSPLogger()

        val template = "{{{  }}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(listOf("Expected non empty placeholder at line 0 position 3"), logger.errors)
    }

    @Test
    fun `should emit error on unsupported placeholder type`() {
        val logger = FakeKSPLogger()

        val template = "{{& component }}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(listOf("cannot handle placeholder type char '&' at line 0 position 3"), logger.errors)
    }

    @Test
    fun `should emit error on unsupported lambda name`() {
        val logger = FakeKSPLogger()

        val template = "{{#second}} test {{/second}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(
            expected = listOf("expected 'first' lambda placeholder but found 'second' at line 0 position 11"),
            actual = logger.errors,
        )
    }

    @Test
    fun `should emit error on unsupported lambda name at lambda close component`() {
        val logger = FakeKSPLogger()

        val template = "{{#first}} test {{/second}}"
        val actual = AddressTemplateParser(logger, FakeKSNode(), template).parse()

        assertNull(actual)
        assertContentEquals(
            expected = listOf("expected 'first' lambda placeholder but found 'second' at line 0 position 27"),
            actual = logger.errors,
        )
    }
}
