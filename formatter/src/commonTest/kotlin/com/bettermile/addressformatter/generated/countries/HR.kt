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

public class HR {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun hotel_in_Dubrovnik_42_64286_18_10394() {
    // description: hotel in Dubrovnik - 42.64286,18.10394
    val components = mapOf("city" to "Dubrovnik", "country" to "Croatia", "country_code" to "hr",
        "county" to "Dubrovnik-Neretva", "house_number" to "2", "postcode" to "20000",
        "road" to "Marijana Blazica", "suburb" to "Pile")
    val expected = """
        |Marijana Blazica 2
        |20000 Dubrovnik
        |Croatia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
