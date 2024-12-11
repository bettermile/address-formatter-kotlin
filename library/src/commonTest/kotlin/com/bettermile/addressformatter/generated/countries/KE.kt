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

public class KE {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Building_in_downtown_Nairobi_1_28599_36_82206() {
    // description: Building in downtown Nairobi -1.28599,36.82206
    val components = mapOf("building" to "Jubilee Insurance Place", "city" to "Nairobi", "country" to "Kenya", "country_code" to "ke", "postcode" to "46464", "road" to "Wabera Street", "state" to "Nairobi", "suburb" to "Madaraka Estate")
    val expected = """
        |Jubilee Insurance Place
        |Wabera Street
        |Nairobi
        |46464
        |Kenya
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
