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

public class ID {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun bar_in_Jakarta() {
    // description: bar in Jakarta
    val components = mapOf("bar" to "Moodz Gastro Bar", "city" to "RW 05",
        "city_district" to "1. kerület", "country" to "Indonesia", "country_code" to "ID",
        "postcode" to "12960", "road" to "Epicentrum Boulevard Barat",
        "state" to "Jakarta Special Capital Region")
    val expected = """
        |Moodz Gastro Bar
        |Epicentrum Boulevard Barat
        |1. kerület
        |RW 05 12960
        |Jakarta Special Capital Region
        |Indonesia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
