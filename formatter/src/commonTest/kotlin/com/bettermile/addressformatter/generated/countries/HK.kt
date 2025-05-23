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

public class HK {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Cinema_in_HK_22_29987_114_17933() {
    // description: Cinema in HK, 22.29987,114.17933
    val components = mapOf("cinema" to "Chinachem Golden Plaza Cinema", "country" to "Hong Kong",
        "country_code" to "hk", "county" to "Yau Tsim Mong District",
        "road" to "Science Museum Road", "state" to "Hong Kong", "state_district" to "Kowloon",
        "suburb" to "Tsim Sha Tsui")
    val expected = """
        |Chinachem Golden Plaza Cinema
        |Science Museum Road
        |Kowloon
        |Hong Kong
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun just_city_no_state() {
    // description: just city, no state
    val components = mapOf("city" to "Hong Kong", "country" to "Hong Kong", "country_code" to "hk",
        "road" to "211 Test Street")
    val expected = """
        |211 Test Street
        |Hong Kong
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
