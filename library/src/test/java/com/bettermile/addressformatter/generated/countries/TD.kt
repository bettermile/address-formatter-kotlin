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

public class TD {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Hotel_in_N_Djamena_12_11417_15_03121() {
    // description: Hotel in N'Djamena, 12.11417,15.03121
    val components = mapOf("city" to "N'Djamena", "country" to "Chad", "country_code" to "td", "hotel" to "Le Meridien - Chari", "postcode" to "456", "road" to "Boulevard de Strasbourg", "state" to "N'Djamena Region")
    val expected = """
        |Le Meridien - Chari
        |Boulevard de Strasbourg
        |N'Djamena
        |Chad
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}