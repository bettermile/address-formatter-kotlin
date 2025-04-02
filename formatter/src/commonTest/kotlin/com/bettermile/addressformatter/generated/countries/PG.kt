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

public class PG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Hotel_in_Port_Moresby_9_47861_147_15138() {
    // description: Hotel in Port Moresby, -9.47861,147.15138
    val components = mapOf("city" to "Port Moresby", "country" to "Papua New Guinea",
        "country_code" to "pg", "county" to "National Capital District",
        "hotel" to "Crowne Plaza Hotel", "road" to "Mary Street",
        "state" to "National Capital District", "suburb" to "Ela Beach")
    val expected = """
        |Crowne Plaza Hotel
        |Mary Street
        |Port Moresby National Capital District
        |Papua New Guinea
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
