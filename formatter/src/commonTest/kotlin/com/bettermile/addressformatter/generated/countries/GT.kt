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

public class GT {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Guatemala_City_14_63133_90_51752() {
    // description: Bank in Guatemala City, 14.63133,-90.51752
    val components = mapOf("bank" to "Banco Reformador", "city" to "Guatemala City",
        "country" to "Guatemala", "country_code" to "gt", "county" to "Guatemala City",
        "house_number" to "18 calle 3-70 Zona 1", "postcode" to "01004",
        "road" to "Transmetro Eje Sur", "state" to "Guatemala", "suburb" to "Zona 1")
    val expected = """
        |Banco Reformador
        |Transmetro Eje Sur 18 calle 3-70 Zona 1
        |01004-Guatemala City
        |Guatemala
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
