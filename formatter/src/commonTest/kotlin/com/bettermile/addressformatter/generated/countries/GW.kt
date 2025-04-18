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

public class GW {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Embassy_in_Bissau_11_86213_15_58436() {
    // description: Embassy in Bissau, 11.86213,-15.58436
    val components = mapOf("building" to "Embaixada d'Espanha", "city" to "Bissau",
        "country" to "Guinea-Bissau", "country_code" to "gw", "neighbourhood" to "Tchada",
        "road" to "Praça dos Herois Nacionais", "state" to "Setor autónomo de Bissau",
        "suburb" to "Reino")
    val expected = """
        |Embaixada d'Espanha
        |Praça dos Herois Nacionais
        |Bissau
        |Guinea-Bissau
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
