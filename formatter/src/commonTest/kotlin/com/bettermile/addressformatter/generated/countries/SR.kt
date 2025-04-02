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

public class SR {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun School_in_Paramaribo_5_83041_55_18020() {
    // description: School in Paramaribo, 5.83041,-55.18020
    val components = mapOf("city" to "Paramaribo", "country" to "Suriname", "country_code" to "sr",
        "county" to "Distrikt Paramaribo", "house_number" to "7", "postcode" to "597",
        "road" to "Simonsweg", "school" to "R.D. Simonsschool")
    val expected = """
        |R.D. Simonsschool
        |Simonsweg 7
        |Paramaribo
        |Suriname
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
