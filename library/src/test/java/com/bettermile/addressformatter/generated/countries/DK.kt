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

public class DK {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun `54_89107_10_40924`() {
    // description: 54.89107,10.40924
    val components = mapOf("country" to "Denmark", "country_code" to "dk", "county" to "Ærø Municipality", "house_number" to "17A", "neighbourhood" to "Paradiset", "postcode" to "5970", "road" to "Baggårde", "state" to "Region of Southern Denmark", "village" to "Ærøskøbing")
    val expected = """
        |Baggårde 17A
        |5970 Ærøskøbing
        |Denmark
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
