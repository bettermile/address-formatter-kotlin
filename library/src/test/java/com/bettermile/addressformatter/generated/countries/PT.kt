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

public class PT {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun `38_74547_9_18519`() {
    // description: 38.74547,-9.18519
    val components = mapOf("city" to "Lisbon", "country" to "Portugal", "country_code" to "pt", "county" to "Lisboa", "house_number" to "11", "postcode" to "1500-203", "road" to "Avenida Conselheiro Barjona de Freitas", "suburb" to "Benfica")
    val expected = """
        |Avenida Conselheiro Barjona de Freitas 11
        |1500-203 Lisbon
        |Portugal
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun `38_72132_9_13017`() {
    // description: 38.72132,-9.13017
    val components = mapOf("city" to "Lisbon", "country" to "Portugal", "country_code" to "pt", "county" to "Lisbon", "house_number" to "2", "neighbourhood" to "São Vicente de Fora", "postcode" to "1170169", "road" to "Rua da Graça", "suburb" to "São Vicente")
    val expected = """
        |Rua da Graça 2
        |1170-169 Lisbon
        |Portugal
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun `37_74851_25_60450`() {
    // description: 37.74851,-25.60450
    val components = mapOf("archipelago" to "Azores", "city" to "Ponta Delgada", "city_district" to "Rosto do Cão (Livramento)", "country" to "Portugal", "country_code" to "pt", "guest_house" to "Casa Do Populo", "house_number" to "1015", "neighbourhood" to "Livramento", "postcode" to "9500-614", "road" to "Rua Padre Domingos da Silva Costay", "state_district" to "São Miguel")
    val expected = """
        |Casa Do Populo
        |Rua Padre Domingos da Silva Costay 1015
        |9500-614 Ponta Delgada
        |Azores
        |Portugal
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
