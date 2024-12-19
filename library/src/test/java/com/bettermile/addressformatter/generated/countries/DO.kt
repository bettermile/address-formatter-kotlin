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

public class DO {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Santo_Domingo() {
    // description: Bank in Santo Domingo
    val components = mapOf("bank" to "Banco de Reservas", "city" to "Santo Domingo", "country" to "Dominican Republic", "country_code" to "do", "neighbourhood" to "El Manguito", "postcode" to "10102", "road" to "Avenida Jiménez Moya", "state" to "Distrito Nacional", "suburb" to "Ensanche Evoristo Morales")
    val expected = """
        |Banco de Reservas
        |Avenida Jiménez Moya
        |Ensanche Evoristo Morales
        |Santo Domingo, DN
        |10102
        |Dominican Republic
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}