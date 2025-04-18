// Copyright 2022 GLS eCom Lab GmbH
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
// THIS FILE IS AUTOGENERATED, PLEASE DON'T CHANGE IT MANUALLY
package com.bettermile.addressformatter.generated.countries

import com.bettermile.addressformatter.AddressFormatter
import kotlin.test.Test
import kotlin.test.assertEquals

public class ES {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Barcelona() {
    // description: Barcelona
    val components = mapOf("city" to "Barcelona", "country" to "Spain", "country_code" to "es",
        "county" to "BCN", "state" to "Catalonia")
    val expected = """
        |Barcelona
        |Catalonia
        |Spain
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun `41_39266_2_19790`() {
    // description: 41.39266,2.19790
    val components = mapOf("city" to "Barcelona", "country" to "Spain", "country_code" to "es",
        "county" to "BCN", "house_number" to "17", "pedestrian" to "Avinguda del Bogatell",
        "postcode" to "08005", "public_building" to "Biblioteca Xavier Benguerel",
        "state" to "Catalonia", "suburb" to "la Vila Olímpica del Poblenou")
    val expected = """
        |Biblioteca Xavier Benguerel
        |Avinguda del Bogatell, 17
        |08005 Barcelona
        |Spain
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Donostia_multipostcode_reject_long_postcode() {
    // description: Donostia multipostcode, reject long postcode
    val components = mapOf("city" to "San Sebastián", "country" to "Spain", "country_code" to "es",
        "county" to "Gipuzkoa",
        "postcode" to "20001;20002;20003;20004;20005;20006;20007;20008;20009;20010;20011;20012;20013;20014;20015;20016;20017;20018",
        "state" to "Basque Country")
    val expected = """
        |San Sebastián
        |Basque Country
        |Spain
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Costa_del_Sol_Occidental() {
    // description: Costa del Sol Occidental
    val components = mapOf("country" to "Spanien", "country_code" to "es",
        "county" to "Costa del Sol Occidental", "state" to "Andalusien")
    val expected = """
        |Costa del Sol Occidental
        |Andalusien
        |Spanien
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
