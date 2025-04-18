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

public class EC {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun University_building_in_Quito_0_19972_78_50708() {
    // description: University building in Quito, -0.19972,-78.50708
    val components = mapOf("building" to "Centro de Matematica", "city" to "Quito",
        "city_district" to "Quito", "country" to "Ecuador", "country_code" to "ec",
        "county" to "Quito", "postcode" to "170521", "road" to "Jerónimo Leiton",
        "state" to "Pichincha", "suburb" to "Rumipamba")
    val expected = """
        |Centro de Matematica
        |Jerónimo Leiton
        |170521
        |Quito
        |Ecuador
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
