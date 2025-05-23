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

public class JE {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Building_in_Saing_Helier_49_18619_2_10716() {
    // description: Building in Saing Helier, 49.18619,-2.10716
    val components = mapOf("country" to "Jersey", "country_code" to "je", "house_number" to "5",
        "postcode" to "JE2 4TN", "road" to "Le Geyt Street", "state" to "Jersey",
        "town" to "Saint Helier")
    val expected = """
        |5 Le Geyt Street
        |Saint Helier
        |JE2 4TN
        |Jersey, Channel Islands
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
