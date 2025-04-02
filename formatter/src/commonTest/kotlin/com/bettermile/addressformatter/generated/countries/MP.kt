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

public class MP {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Red_Cross_office_on_Saipan_15_12601_145_72340() {
    // description: Red Cross office on Saipan, 15.12601,145.72340
    val components = mapOf("building" to "Red Cross", "country" to "Northern Mariana Islands",
        "country_code" to "mp", "county" to "Saipan Municipality", "postcode" to "96950",
        "road" to "Chalan Tun Herman Pan", "state" to "Northern Mariana Islands",
        "town" to "Saipan")
    val expected = """
        |Red Cross
        |Chalan Tun Herman Pan
        |Saipan, MP 96950
        |United States of America
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
