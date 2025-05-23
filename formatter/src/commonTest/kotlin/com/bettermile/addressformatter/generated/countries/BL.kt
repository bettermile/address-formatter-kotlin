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

public class BL {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun House_in_Gustavia_17_89548_62_85177() {
    // description: House in Gustavia, 17.89548,-62.85177
    val components = mapOf("country" to "Saint Barthélemy", "country_code" to "bl",
        "house_number" to "15", "neighbourhood" to "La Pointe", "postcode" to "97133",
        "road" to "Rue de Pére Irénée de Bruyn", "state" to "Saint Barthélemy",
        "town" to "Gustavia")
    val expected = """
        |15 Rue de Pére Irénée de Bruyn
        |97133 Gustavia
        |Saint-Barthélemy, France
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
