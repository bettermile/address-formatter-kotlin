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

public class CX {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Supermarket_on_Christmas_Island_10_4230_105_6729() {
    // description: Supermarket on Christmas Island, -10.4230,105.6729
    val components = mapOf("country" to "Christmas Island", "country_code" to "cx",
        "neighbourhood" to "The Settlement", "postcode" to "6798", "road" to "Gaze Road",
        "state" to "Christmas Island", "supermarket" to "Christmas Island Supermarket")
    val expected = """
        |Christmas Island Supermarket
        |Gaze Road
        |Christmas Island 6798
        |Australia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
