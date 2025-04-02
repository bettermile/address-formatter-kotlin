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

public class TG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Govt_building_in_Lom_6_12259_1_21944() {
    // description: Govt building in Lomé, 6.12259,1.21944
    val components = mapOf("city" to "Lomé", "country" to "Togo", "country_code" to "tg",
        "postcode" to "BP: 353", "public_building" to "Palais de Justice",
        "road" to "Aveenue de la Cooperation", "state" to "Maritime Region",
        "suburb" to "Hanoukopé")
    val expected = """
        |Palais de Justice
        |Aveenue de la Cooperation
        |Lomé
        |Togo
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
