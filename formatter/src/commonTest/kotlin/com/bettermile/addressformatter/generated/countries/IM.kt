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

public class IM {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Address_in_Douglas_54_14906_4_47949() {
    // description: Address in Douglas, 54.14906,-4.47949
    val components = mapOf("address29" to "3FM", "country" to "Isle of Man", "country_code" to "im",
        "county" to "Middle", "house_number" to "45", "postcode" to "IM1 2AY",
        "road" to "Victoria Street", "town" to "Douglas")
    val expected = """
        |3FM
        |45 Victoria Street
        |Douglas
        |IM1 2AY
        |Isle of Man
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
