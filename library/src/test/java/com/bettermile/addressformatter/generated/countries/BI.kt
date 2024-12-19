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

public class BI {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Office_in_Bujumbura_3_38341_29_36166() {
    // description: Office in Bujumbura, -3.38341,29.36166
    val components = mapOf("building" to "Bonauto s.a.", "city" to "Bujumbura", "country" to "Burundi", "country_code" to "bi", "house_number" to "17", "postcode" to "2730", "road" to "Avenue du Commerce", "state" to "Bujumbura Mairie", "suburb" to "Building Bata")
    val expected = """
        |Bonauto s.a.
        |Avenue du Commerce 17
        |Bujumbura
        |Burundi
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}