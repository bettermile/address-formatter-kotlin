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

public class SV {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Government_building_in_San_Salvador_13_69928_89_19845() {
    // description: Government building in San Salvador, 13.69928,-89.19845
    val components = mapOf("building" to "Ministerio de Salud", "city" to "San Salvador",
        "country" to "El Salvador", "country_code" to "sv", "house_number" to "827",
        "neighbourhood" to "Centro Urbano IVU", "postcode" to "503", "road" to "Calle Arce",
        "state" to "Departamento de San Salvador", "suburb" to "Comunidad Tutunichapa")
    val expected = """
        |Ministerio de Salud
        |Calle Arce 827
        |503 - San Salvador
        |Departamento de San Salvador
        |El Salvador
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun no_floating_dash_if_no_postcode() {
    // description: no floating dash if no postcode
    val components = mapOf("building" to "Ministerio de Salud", "city" to "San Salvador",
        "country" to "El Salvador", "country_code" to "sv", "house_number" to "827",
        "neighbourhood" to "Centro Urbano IVU", "road" to "Calle Arce",
        "state" to "Departamento de San Salvador", "suburb" to "Comunidad Tutunichapa")
    val expected = """
        |Ministerio de Salud
        |Calle Arce 827
        |San Salvador
        |Departamento de San Salvador
        |El Salvador
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
