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

public class SG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun SG_no_postcode() {
    // description: SG no postcode
    val components = mapOf("country" to "Singapore", "country_code" to "SG", "house_number" to "76",
        "neighbourhood" to "Chinatown", "road" to "Shenton Way", "suburb" to "Shenton Way")
    val expected = """
        |76 Shenton Way
        |Singapore
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Chinatown_Singapore() {
    // description: Chinatown Singapore
    val components = mapOf("country" to "Singapore", "country_code" to "SG", "city" to "Singapore",
        "postcode" to "546080", "house_number" to "16", "road" to "Sandilands Road")
    val expected = """
        |16 Sandilands Road
        |Singapore 546080
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun no_city_1_30585_103_78158() {
    // description: no city, 1.30585,103.78158
    val components = mapOf("country" to "Singapore", "country_code" to "sg",
        "county" to "Southwest", "house_number" to "26", "postcode" to "130026",
        "residential" to "Dover Gardens", "road" to "Dover Crescent", "suburb" to "Queenstown")
    val expected = """
        |26 Dover Crescent, Dover Gardens
        |Singapore 130026
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun wrong_city_1_30585_103_78158() {
    // description: wrong city, 1.30585,103.78158
    val components = mapOf("city" to "Jurong East", "country" to "Singapore",
        "country_code" to "sg", "county" to "Southwest", "house_number" to "26",
        "postcode" to "130026", "residential" to "Dover Gardens", "road" to "Dover Crescent",
        "suburb" to "Queenstown")
    val expected = """
        |26 Dover Crescent, Dover Gardens
        |Singapore 130026
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
