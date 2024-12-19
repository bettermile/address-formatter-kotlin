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

public class RS {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun restaurant_in_Belgrade() {
    // description: restaurant in Belgrade
    val components = mapOf("restaurant" to "Ресторан Лидо", "city" to "Belgrade", "country" to "Serbia", "country_code" to "RS", "neighbourhood" to "Donji Grad", "house_number" to "2", "postcode" to "11080", "road" to "Goce Delceva", "state" to "Central Serbia", "suburb" to "Zemun")
    val expected = """
        |Ресторан Лидо
        |Goce Delceva 2
        |11080 Belgrade
        |Serbia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}