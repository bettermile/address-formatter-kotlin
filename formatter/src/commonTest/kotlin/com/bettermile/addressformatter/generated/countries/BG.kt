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

public class BG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun university_in_Sofia() {
    // description: university in Sofia
    val components = mapOf("city" to "Sofia", "country" to "Bulgaria", "country_code" to "BG",
        "county" to "Sofia-City Region", "road" to "Georgi Sava Rakovski St.", "state" to "RJ",
        "state_district" to "Região Metropolitana do Rio de Janeiro", "suburb" to "Centre",
        "postcode" to "1000",
        "university" to "Krastyo Sarafov National Academy for Theatre and Film Arts")
    val expected = """
        |Krastyo Sarafov National Academy for Theatre and Film Arts
        |Georgi Sava Rakovski St.
        |Centre
        |Sofia 1000
        |Bulgaria
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
