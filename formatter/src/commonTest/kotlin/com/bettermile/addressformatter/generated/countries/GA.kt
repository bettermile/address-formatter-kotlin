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

public class GA {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Embassy_in_Librevill_0_38899_9_44561() {
    // description: Embassy in Librevill, 0.38899,9.44561
    val components = mapOf("building" to "Consulat De France", "city" to "Libreville",
        "country" to "Gabon", "country_code" to "ga", "county" to "Libreville Department",
        "neighbourhood" to "Batavea", "postcode" to "BP13131", "road" to "Rue Ange M'ba",
        "state" to "Estuaire", "suburb" to "Nombakele")
    val expected = """
        |Consulat De France
        |Rue Ange M'ba
        |Nombakele
        |Libreville
        |Gabon
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
