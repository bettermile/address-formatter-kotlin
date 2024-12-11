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

public class MG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Embassy_in_Antananarivo_18_90744_47_52145() {
    // description: Embassy in Antananarivo, -18.90744,47.52145
    val components = mapOf("building" to "Ambassade de l'Inde", "city" to "Antananarivo", "country" to "Madagascar", "country_code" to "mg", "postcode" to "1", "road" to "Làlana Rajhonson Emile", "state" to "Province d'Antananarivo", "suburb" to "Tsaralalana")
    val expected = """
        |Ambassade de l'Inde
        |Làlana Rajhonson Emile
        |Tsaralalana
        |1 Antananarivo
        |Madagascar
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
