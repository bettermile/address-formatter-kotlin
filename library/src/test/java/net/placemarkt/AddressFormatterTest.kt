/*
 * Copyright (c) 2020 Pirbright Software
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

package net.placemarkt

import net.placemarkt.generated.testCases
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException

@RunWith(Enclosed::class)
class AddressFormatterTest {
    @RunWith(Parameterized::class)
    class ParameterizedAddressFormatterTest(testCase: TestCase, @Suppress("UNUSED_PARAMETER") testName: String?) {
        private val components: Map<String, String> = testCase.components
        private val address: String = testCase.expected

        @Test
        fun worksWithAddressesWorldwide() {
            val formatted = formatter.format(components)
            Assert.assertEquals(address, formatted)
        }

        companion object {
            lateinit var formatter: AddressFormatter

            @BeforeClass
            @JvmStatic
            fun setup() {
                formatter = AddressFormatter(abbreviate = false, appendCountry = false)
            }

            @JvmStatic
            @Parameterized.Parameters(name = "{1}")
            fun addresses(): Collection<Array<Any>> {
                return testCases.map { arrayOf(it, "${it.fileName} - ${it.description}") }
            }
        }
    }

    class SingleTests {
        @Test
        fun dealsWithEmptyStringCorrectly() {
            val json = ""
            val error = Assert.assertThrows(IOException::class.java) { formatter.format(json) }
            Assert.assertEquals("Json processing exception", error.message)
        }

        @Test
        fun dealsWithImproperlyFormatterJsonCorrectly() {
            val json = "{"
            val error = Assert.assertThrows(IOException::class.java) { formatter.format(json) }
            Assert.assertEquals("Json processing exception", error.message)
        }

        @Test
        @Throws(Exception::class)
        fun correctlySetsFallbackCountryCode() {
            val json = """
                {city: 'Antwerp',
                city_district: 'Antwerpen',
                country: 'Belgium',
                country_code: 'yu',
                county: 'Antwerp',
                house_number: 63,
                neighbourhood: 'Sint-Andries',
                postcode: 2000,
                restaurant: 'Meat & Eat',
                road: 'Vrijheidstraat',
                state: 'Flanders'}
                """.trimIndent()
            val formatted = formatter.format(json, "US")
            Assert.assertEquals(
                """
                Meat & Eat
                63 Vrijheidstraat
                Antwerp, Flanders 2000
                Belgium
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun correctlyAppendsCountry() {
            val json = """
                {houseNumber: '301',
                road: 'Hamilton Avenue',
                neighbourhood: 'Crescent Park',
                city: 'Palo Alto',
                postcode: '94303',
                county: 'Santa Clara County',
                state: 'California',
                countryCode: 'US',}
                """.trimIndent()
            val formatted = formatterWithAppendCountryFlag.format(json)
            Assert.assertEquals(
                """
                301 Hamilton Avenue
                Palo Alto, CA 94303
                United States of America
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun correctlyAbbreviatesAvenue() {
            val json = """
                {country_code: 'US',
                house_number: '301',
                road: 'Hamilton Avenue',
                neighbourhood: 'Crescent Park',
                city: 'Palo Alto',
                postcode: '94303',
                county: 'Santa Clara County',
                state: 'California',
                country: 'United States',}
                """.trimIndent()
            val formatted = formatterWithAbbreviationFlag.format(json)
            Assert.assertEquals(
                """
                301 Hamilton Ave
                Palo Alto, CA 94303
                United States of America
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun correctlyAbbreviatesRoad() {
            val json = """
                {country_code: 'US',
                house_number: '301',
                road: 'Northwestern University Road',
                neighbourhood: 'Crescent Park',
                city: 'Palo Alto',
                postcode: '94303',
                county: 'Santa Clara County',
                state: 'California',
                country: 'United States'}
                """.trimIndent()
            val formatted = formatterWithAbbreviationFlag.format(json)
            Assert.assertEquals(
                """
                301 Northwestern University Rd
                Palo Alto, CA 94303
                United States of America
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun doesNotAbbreviateWhenComponentNotPresent() {
            val json = """
                {country_code: 'US',
                house_number: '301',
                neighbourhood: 'Crescent Park',
                city: 'Palo Alto',
                postcode: '94303',
                county: 'Santa Clara County',
                state: 'California',
                country: 'United States'}
                """.trimIndent()
            val formatted = formatterWithAbbreviationFlag.format(json)
            Assert.assertEquals(
                """
                301
                Palo Alto, CA 94303
                United States of America
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun correctlyAbbreviatesCAAvenue() {
            val json = """
                {city: 'Vancouver',
                country: 'Canada',
                country_code: 'ca',
                county: 'Greater Vancouver Regional District',
                postcode: 'V6K',
                road: 'Cornwall Avenue',
                state: 'British Columbia',
                suburb: 'Kitsilano',}
                """.trimIndent()
            val formatted = formatterWithAbbreviationFlag.format(json)
            Assert.assertEquals(
                """
                Cornwall Ave
                Vancouver, BC V6K
                Canada
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun correctlyAbbreviatesESCarrer() {
            val json = """
                {city: 'Barcelona',
                city_district: 'Sarrià - Sant Gervasi',
                country: 'Spain',
                country_code: 'es',
                county: 'BCN',
                house_number: '68',
                neighbourhood: 'Sant Gervasi',
                postcode: '08017',
                road: 'Carrer de Calatrava',
                state: 'Catalonia',
                suburb: 'les Tres Torres'}
                """.trimIndent()
            val formatted = formatterWithAbbreviationFlag.format(json)
            Assert.assertEquals(
                """
                C Calatrava, 68
                08017 Barcelona
                Spain
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun camelCaseWorks() {
            val json = """
                {city: 'Budapest',
                cityDistrict: '1. kerület',
                country: 'Hungary',
                countryCode: 'hu',
                county: 'Budapesti kistérség',
                houseNumber: 11,
                neighbourhood: 'Naphegy',
                postcode: 1111,
                road: 'Dezső utca',
                state: 'Közép-Magyarország',
                stateDistrict: 'Central Hungary',
                suburb: 'Krisztinaváros'}
                """.trimIndent()
            val formatted = formatter.format(json)
            Assert.assertEquals(
                """
                1111 Budapest
                Dezső utca 11.
                Hungary
                
                """.trimIndent(),
                formatted,
            )
        }

        @Test
        @Throws(Exception::class)
        fun useReplacementFormat() {
            val json = """
                {city: 'Budapest',
                cityDistrict: '1. kerület',
                country: 'Hungary',
                countryCode: 'hu',
                county: 'Budapesti kistérség',
                houseNumber: 11,
                neighbourhood: 'Naphegy',
                postcode: 1111,
                road: 'Dezső utca',
                state: 'Közép-Magyarország',
                stateDistrict: 'Central Hungary',
                suburb: 'Krisztinaváros'}
                """.trimIndent()
            val replacements = mapOf("HU" to "{{country}}, {{house_number}}:{{postcode}}")
            val formatted =
                AddressFormatter(abbreviate = false, appendCountry = false, replacementFormats = replacements)
                    .format(json)
            Assert.assertEquals("Hungary, 11:1111\n", formatted)
        }

        @Test(expected = IllegalArgumentException::class)
        fun failOnInvalidCountryCodeInReplacements() {
            val replacements = mapOf("ZZ" to "{{country}}, {{house_number}}:{{postcode}}")
            AddressFormatter(abbreviate = false, appendCountry = false, replacementFormats = replacements)
        }

        companion object {
            lateinit var formatter: AddressFormatter
            lateinit var formatterWithAppendCountryFlag: AddressFormatter
            lateinit var formatterWithAbbreviationFlag: AddressFormatter

            @BeforeClass
            @JvmStatic
            fun setup() {
                formatter = AddressFormatter(abbreviate = false, appendCountry = false)
                formatterWithAppendCountryFlag = AddressFormatter(abbreviate = false, appendCountry = true)
                formatterWithAbbreviationFlag = AddressFormatter(abbreviate = true, appendCountry = false)
            }
        }
    }
}
