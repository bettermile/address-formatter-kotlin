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

public class VN {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun bar_in_Nha_Trang() {
    // description: bar in Nha Trang
    val components = mapOf("road" to "Tran Quang Khai", "city" to "Nha Trang", "country" to "Vietnam", "country_code" to "VN", "postcode" to "48058", "bar" to "Why Not Bar", "suburb" to "Tan Lap District", "state" to "Khanh Hoa province")
    val expected = """
        |Why Not Bar
        |Tran Quang Khai
        |Tan Lap District
        |Nha Trang
        |Khanh Hoa province 48058
        |Vietnam
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}