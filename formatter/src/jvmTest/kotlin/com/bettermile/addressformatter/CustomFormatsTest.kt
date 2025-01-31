package com.bettermile.addressformatter

import com.bettermile.addressformatter.template.AddressTemplateDefinition
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomFormatsTest {

    @AddressTemplateDefinition(
        """
        {{{postcode}}} {{{road}}}
        {{{house_number}}} {{{city}}}
        """,
    )
    private val customTemplateDE1 = AddressTemplates.customTemplateDE1

    @Test
    fun `should use custom address template when formatting address for the country`() {
        val formatter = AddressFormatter(abbreviate = false, appendCountry = false, mapOf("DE" to customTemplateDE1))

        val actual = formatter.format(
            mapOf(
                "country_code" to "DE",
                "postcode" to "12345",
                "city" to "Berlin",
                "road" to "Any road",
                "house_number" to "123a"
            )
        )

        val expected = """
            12345 Any road
            123a Berlin

        """.trimIndent()
        assertEquals(expected, actual)
    }

    @AddressTemplateDefinition(
        """
        {{{postcode}}} {{{road}}}
        {{{house_number}}} {{{city}}}
        """,
    )
    private val customTemplateDE2 = AddressTemplates.customTemplateDE2

    @Test
    fun `should not use custom address template when formatting address for different country`() {
        val formatter = AddressFormatter(abbreviate = false, appendCountry = false, mapOf("DE" to customTemplateDE2))

        val actual = formatter.format(
            mapOf(
                "country_code" to "FR",
                "postcode" to "12345",
                "city" to "Paris",
                "road" to "Any road",
                "house_number" to "123a"
            )
        )

        val expected = """
            123a Any road
            12345 Paris

        """.trimIndent()
        assertEquals(expected, actual)
    }

    @AddressTemplateDefinition(
        """
        {{{postcode}}} {{{road}}}
        {{{house_number}}} {{{city}}}
        """,
    )
    private val customTemplateDE3 = AddressTemplates.customTemplateDE3

    @Test
    fun `should handle abbreviations for custom templates`() {
        val formatter = AddressFormatter(abbreviate = true, appendCountry = false, mapOf("DE" to customTemplateDE3))

        val actual = formatter.format(
            mapOf(
                "country_code" to "DE",
                "postcode" to "12345",
                "city" to "Berlin",
                "road" to "Berliner Straße",
                "house_number" to "123a"
            )
        )

        val expected = """
            12345 Berliner Str
            123a Berlin

        """.trimIndent()
        assertEquals(expected, actual)
    }
}
