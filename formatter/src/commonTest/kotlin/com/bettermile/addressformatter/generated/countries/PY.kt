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

public class PY {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun _25_2889244_57_6466121() {
    // description: -25.2889244, -57.6466121
    val components = mapOf("bakery" to "Old Germany German Bakery", "city" to "Asuncion",
        "country" to "Paraguay", "country_code" to "py", "postcode" to "1409", "road" to "Milano",
        "state" to "Distrito Capital de Paraguay", "suburb" to "Tacumbú")
    val expected = """
        |Old Germany German Bakery
        |Milano
        |1409 Asuncion
        |Paraguay
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
