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

public class VE {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun restaurant_in_Caracas() {
    // description: restaurant in Caracas
    val components = mapOf("house_number" to "33", "road" to "Av. Teresa de La Parra", "city" to "Parroquia San Pedro", "county" to "Municipio Libertador", "neighbourhood" to "Los Chaguaramos", "country" to "Venezuela", "country_code" to "VE", "postcode" to "1040", "restaurant" to "El Carretón", "state_district" to "Caracas", "state" to "Capital District")
    val expected = """
        |El Carretón
        |Av. Teresa de La Parra 33
        |Parroquia San Pedro 1040, Capital District
        |Venezuela
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
