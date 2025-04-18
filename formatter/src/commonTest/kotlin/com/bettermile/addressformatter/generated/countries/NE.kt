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

public class NE {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Embassy_in_Niamey_13_52012_2_09463() {
    // description: Embassy in Niamey, 13.52012,2.09463
    val components = mapOf("address" to "Embassy of the Federal Republic of Germany",
        "city" to "Niamey", "country" to "Niger", "country_code" to "ne", "house_number" to "71",
        "postcode" to "13416", "road" to "Boulevard Général De Gaulle", "state" to "Niamey",
        "suburb" to "Plateau", "village" to "Néini Goungou")
    val expected = """
        |Embassy of the Federal Republic of Germany
        |71
        |Boulevard Général De Gaulle
        |Niamey
        |Niger
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
