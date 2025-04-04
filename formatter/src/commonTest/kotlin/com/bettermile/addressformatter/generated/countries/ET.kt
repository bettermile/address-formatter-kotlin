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

public class ET {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Hotel_in_Swakopmund_22_67877_14_52302() {
    // description: Hotel in Swakopmund, -22.67877,14.52302
    val components = mapOf("city" to "Addis Abeba", "country" to "Ethiopia", "country_code" to "et",
        "library" to "National Library & Archives", "postcode" to "3001", "road" to "Yared Street",
        "state" to "Addis Ababa", "suburb" to "Beherawi")
    val expected = """
        |National Library & Archives
        |Yared Street
        |3001 Addis Abeba
        |Ethiopia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
