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

public class FI {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun restaurant_Helsinki() {
    // description: restaurant Helsinki
    val components = mapOf("city" to "Helsinki", "country" to "Finland", "country_code" to "fi",
        "county" to "Helsingin seutukunta", "house_number" to "15", "postcode" to "00140",
        "restaurant" to "Ravintola Central", "road" to "Pietarinkatu",
        "state" to "Southern Finland", "state_district" to "Southern Finland",
        "suburb" to "Ullanlinna")
    val expected = """
        |Ravintola Central
        |Pietarinkatu 15
        |00140 Helsinki
        |Finland
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
