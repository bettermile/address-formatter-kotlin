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

package com.bettermile.addressformatter

import com.bettermile.addressformatter.generated.testCases
import org.junit.Assert
import org.junit.BeforeClass
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

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
        fun correctlySetsFallbackCountryCode() {
            val json = mapOf(
                "city" to "Antwerp",
                "city_district" to "Antwerpen",
                "country" to "Belgium",
                "country_code" to "yu",
                "county" to "Antwerp",
                "house_number" to 63,
                "neighbourhood" to "Sint-Andries",
                "postcode" to 2000,
                "restaurant" to "Meat & Eat",
                "road" to "Vrijheidstraat",
                "state" to "Flanders",
            )
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
        fun correctlyAppendsCountry() {
            val json = mapOf(
                "houseNumber" to "301",
                "road" to "Hamilton Avenue",
                "neighbourhood" to "Crescent Park",
                "city" to "Palo Alto",
                "postcode" to "94303",
                "county" to "Santa Clara County",
                "state" to "California",
                "countryCode" to "US",
            )
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
        fun correctlyAbbreviatesAvenue() {
            val json = mapOf(
                "country_code" to "US",
                "house_number" to "301",
                "road" to "Hamilton Avenue",
                "neighbourhood" to "Crescent Park",
                "city" to "Palo Alto",
                "postcode" to "94303",
                "county" to "Santa Clara County",
                "state" to "California",
                "country" to "United States",
            )
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
        fun correctlyAbbreviatesRoad() {
            val json = mapOf(
                "country_code" to "US",
                "house_number" to "301",
                "road" to "Northwestern University Road",
                "neighbourhood" to "Crescent Park",
                "city" to "Palo Alto",
                "postcode" to "94303",
                "county" to "Santa Clara County",
                "state" to "California",
                "country" to "United States",
            )
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
        fun doesNotAbbreviateWhenComponentNotPresent() {
            val json = mapOf(
                "country_code" to "US",
                "house_number" to "301",
                "neighbourhood" to "Crescent Park",
                "city" to "Palo Alto",
                "postcode" to "94303",
                "county" to "Santa Clara County",
                "state" to "California",
                "country" to "United States",
            )
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
        fun correctlyAbbreviatesCAAvenue() {
            val json = mapOf(
                "city" to "Vancouver",
                "country" to "Canada",
                "country_code" to "ca",
                "county" to "Greater Vancouver Regional District",
                "postcode" to "V6K",
                "road" to "Cornwall Avenue",
                "state" to "British Columbia",
                "suburb" to "Kitsilano",
            )
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
        fun correctlyAbbreviatesESCarrer() {
            val json = mapOf(
                "city" to "Barcelona",
                "city_district" to "Sarrià - Sant Gervasi",
                "country" to "Spain",
                "country_code" to "es",
                "county" to "BCN",
                "house_number" to "68",
                "neighbourhood" to "Sant Gervasi",
                "postcode" to "08017",
                "road" to "Carrer de Calatrava",
                "state" to "Catalonia",
                "suburb" to "les Tres Torres",
            )
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
        fun camelCaseWorks() {
            val json = mapOf(
                "city" to "Budapest",
                "cityDistrict" to "1. kerület",
                "country" to "Hungary",
                "countryCode" to "hu",
                "county" to "Budapesti kistérség",
                "houseNumber" to 11,
                "neighbourhood" to "Naphegy",
                "postcode" to 1111,
                "road" to "Dezső utca",
                "state" to "Közép-Magyarország",
                "stateDistrict" to "Central Hungary",
                "suburb" to "Krisztinaváros",
            )
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
