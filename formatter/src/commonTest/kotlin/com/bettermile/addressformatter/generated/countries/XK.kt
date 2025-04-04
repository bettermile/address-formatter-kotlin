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

public class XK {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Address_in_Pristina_42_66925_21_16407() {
    // description: Address in Pristina, 42.66925,21.16407
    val components = mapOf("city" to "Pristina", "continent" to "Europe", "country" to "Kosovo",
        "country_code" to "xk", "district" to "District of Prishtina", "house_number" to "13",
        "municipality" to "Municipality of Pristina", "postcode" to "10010",
        "road" to "Ahmet Haxhiu", "suburb" to "Medresa")
    val expected = """
        |13, Ahmet Haxhiu
        |Pristina 10010
        |Kosovo
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
