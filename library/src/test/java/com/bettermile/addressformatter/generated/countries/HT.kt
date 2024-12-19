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

public class HT {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Port_au_Prince_18_55005_72_34510() {
    // description: Bank in Port-au-Prince, 18.55005,-72.34510
    val components = mapOf("bank" to "Banque de l'Union Haitienne SA", "city" to "Commune de Port-au-Prince", "country" to "Haiti", "country_code" to "ht", "postcode" to "HT6114", "road" to "Rue Bonne Foi", "state" to "Département de l'Ouest", "state_district" to "Port-au-Prince", "suburb" to "6e Turgeau")
    val expected = """
        |Banque de l'Union Haitienne SA
        |Rue Bonne Foi
        |HT6114 Port-au-Prince
        |Haiti
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}