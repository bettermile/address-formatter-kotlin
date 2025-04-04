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

public class UG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Kampala_0_31220_32_58532() {
    // description: Bank in Kampala, 0.31220, 32.58532
    val components = mapOf("building" to "Orient Plaza", "city" to "Kampala", "country" to "Uganda",
        "country_code" to "ug", "county" to "Kampala", "house_number" to "6/6A",
        "neighbourhood" to "Nakivubo", "postcode" to "7063", "road" to "Kampala Road",
        "state" to "Central Region", "suburb" to "Kibuli")
    val expected = """
        |Orient Plaza
        |6/6A Kampala Road
        |Kampala
        |Uganda
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun subcounty() {
    // description: subcounty
    val components = mapOf("country" to "Uganda", "country_code" to "ug", "state" to "Arua",
        "subcounty" to "Ayivuni")
    val expected = """
        |Ayivuni
        |Arua
        |Uganda
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
