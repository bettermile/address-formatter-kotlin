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

public class GY {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Georgetown_6_81913_58_16213() {
    // description: Bank in Georgetown, 6.81913,-58.16213
    val components = mapOf("bank" to "Scotiabank (Carmichael St. Branch)", "city" to "Georgetown",
        "country" to "Guyana", "country_code" to "gy", "county" to "Demerara-Mahaica Region",
        "postcode" to "101147", "road" to "Carmichael Street")
    val expected = """
        |Scotiabank (Carmichael St. Branch)
        |Carmichael Street
        |Georgetown
        |Guyana
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
