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

public class AS {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Building_in_Pago_Pago_14_28181_170_68354() {
    // description: Building in Pago Pago, -14.28181,-170.68354
    val components = mapOf("building" to "Executive Office Building (American Samoa Government)",
        "country" to "American Samoa", "country_code" to "as", "county" to "Eastern District",
        "municipality" to "Maʻopūtasi County", "postcode" to "96799", "road" to "Route 118",
        "village" to "Utulei")
    val expected = """
        |Executive Office Building (American Samoa Government)
        |Route 118
        |Utulei
        |Maʻopūtasi County, AS 96799
        |United States of America
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
