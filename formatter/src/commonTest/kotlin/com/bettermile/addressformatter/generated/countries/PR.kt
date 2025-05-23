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

public class PR {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun bar_in_San_Juan() {
    // description: bar in San Juan
    val components = mapOf("bar" to "Wisos Velillas Bar", "house_number" to "252",
        "road" to "Calle San Francisco", "city" to "San Juan Antiguo", "country" to "Puerto Rico",
        "country_code" to "PR", "county" to "San Juan", "postcode" to "00901")
    val expected = """
        |Wisos Velillas Bar
        |252 Calle San Francisco
        |San Juan Antiguo, PR 00901
        |United States of America
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
