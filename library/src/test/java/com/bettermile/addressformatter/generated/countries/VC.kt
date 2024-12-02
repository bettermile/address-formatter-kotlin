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

public class VC {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Kingstown_13_15545_61_22592() {
    // description: Bank in Kingstown, 13.15545,-61.22592
    val components = mapOf("bank" to "Bank of St Vincent and the Grenadines", "city" to "Kingstown", "country" to "Saint Vincent and the Grenadines", "country_code" to "vc", "county" to "Saint George", "road" to "Grenville Street")
    val expected = """
        |Bank of St Vincent and the Grenadines
        |Grenville Street
        |Kingstown
        |Saint Vincent and the Grenadines
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
