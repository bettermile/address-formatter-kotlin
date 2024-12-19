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

public class KN {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Restaurant_in_Basseterre_17_29535_62_72366() {
    // description: Restaurant in Basseterre, 17.29535,-62.72366
    val components = mapOf("country" to "Saint Kitts and Nevis", "country_code" to "kn", "province" to "Saint Kitts", "restaurant" to "Ballahoo Restaurant", "road" to "Fort Street", "town" to "Basseterre")
    val expected = """
        |Ballahoo Restaurant
        |Fort Street
        |Basseterre, Saint Kitts
        |Saint Kitts and Nevis
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}