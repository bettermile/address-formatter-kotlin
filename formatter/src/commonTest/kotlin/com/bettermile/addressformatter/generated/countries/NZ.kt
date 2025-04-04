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

public class NZ {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun House_in_Wellington_41_29789_174_78511() {
    // description: House in Wellington, -41.29789,174.78511
    val components = mapOf("city" to "Wellington", "country" to "New Zealand",
        "country_code" to "nz", "county" to "Wellington City", "house_number" to "53",
        "postcode" to "6011", "road" to "Pirie Street", "state" to "Wellington",
        "suburb" to "Mount Victoria")
    val expected = """
        |53 Pirie Street
        |Mount Victoria
        |Wellington 6011
        |New Zealand
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun City_of_Wellington() {
    // description: City of Wellington
    val components = mapOf("city" to "Wellington", "country" to "New Zealand",
        "country_code" to "nz", "county" to "Wellington City", "state" to "Wellington")
    val expected = """
        |Wellington
        |New Zealand
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
