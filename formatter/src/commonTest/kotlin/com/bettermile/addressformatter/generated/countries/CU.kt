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

public class CU {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Embassy_in_Havana_23_13763_82_38976() {
    // description: Embassy in Havana, 23.13763,-82.38976
    val components = mapOf("building" to "Embajada de Hungría", "city" to "Havana",
        "country" to "Cuba", "country_code" to "cu", "county" to "Plaza de la Revolución",
        "neighbourhood" to "Barrio El Fanguito", "postcode" to "10400", "road" to "Calle 19",
        "state" to "Havana", "suburb" to "El Vedado")
    val expected = """
        |Embajada de Hungría
        |Calle 19
        |Havana, 10400
        |Cuba
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun address_in_Havana_no_city_23_13895_82_36652() {
    // description: address in Havana / no city, 23.13895,-82.36652
    val components = mapOf("country" to "Cuba", "country_code" to "cu", "county" to "Centro Habana",
        "house_number" to "209", "neighbourhood" to "Colón", "road" to "Perseverancia",
        "state" to "Havana")
    val expected = """
        |Perseverancia 209
        |Havana
        |Cuba
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
