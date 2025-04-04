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

public class CW {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Address_in_Willemstad_12_11148_68_94561() {
    // description: Address in Willemstad, 12.11148,-68.94561
    val components = mapOf("country" to "Curaçao", "country_code" to "cw", "house_number" to "14",
        "neighbourhood" to "Colon", "postcode" to "0000NA", "road" to "Jan Erasmusstraat",
        "state" to "Curaçao", "suburb" to "Seru Domi", "town" to "Willemstad")
    val expected = """
        |Jan Erasmusstraat 14
        |Willemstad
        |Curaçao
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
