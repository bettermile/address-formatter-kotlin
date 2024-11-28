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

public class FO {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_T_rshavn_62_01111_6_77396() {
    // description: Bank in Tórshavn, 62.01111,-6.77396
    val components = mapOf("bank" to "Bank Nordik", "country" to "Territorial waters of Faroe Islands", "country_code" to "fo", "house_number" to "17", "postcode" to "100", "road" to "Steinagøta", "state" to "Streymoy region", "town" to "Tórshavn")
    val expected = """
        |Bank Nordik
        |Steinagøta 17
        |100 Tórshavn
        |Faroe Islands
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
