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

public class BO {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_La_Paz_16_49607_68_14860() {
    // description: Bank in La Paz, -16.49607,-68.14860
    val components = mapOf("bank" to "Banco Los Andes Pro Credite",
        "city" to "Municipio Nuestra Senora de La Paz", "country" to "Bolivia",
        "country_code" to "bo", "county" to "Provincia Murillo", "postcode" to "8686",
        "road" to "José María Achá", "state" to "La Paz Departament", "suburb" to "Gran Poder")
    val expected = """
        |Banco Los Andes Pro Credite
        |José María Achá
        |La Paz
        |Bolivia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
