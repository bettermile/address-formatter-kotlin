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

public class OM {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Supermarket_23_6004_58_5395() {
    // description: Supermarket, 23.6004,58.5395
    val components = mapOf("city" to "Muscat, Oman", "country" to "Oman", "country_code" to "om",
        "postcode" to "116", "residential" to "Muscat", "road" to "Al Noor Street",
        "state" to "Muscat", "supermarket" to "Fathima Super Market")
    val expected = """
        |Fathima Super Market
        |Al Noor Street
        |116
        |Muscat, Oman
        |Muscat
        |Oman
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Supermarket_language_ar_23_6004_58_5395() {
    // description: Supermarket, language=ar, 23.6004,58.5395
    val components = mapOf("city" to "Muscat, Oman", "country" to "سلطنة عمان",
        "country_code" to "om", "postcode" to "116", "residential" to "Muscat",
        "road" to "Al Noor Street", "state" to "محافظة مسقط",
        "supermarket" to "Fathima Super Market")
    val expected = """
        |Fathima Super Market
        |Al Noor Street
        |116
        |Muscat, Oman
        |محافظة مسقط
        |سلطنة عمان
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Bakery_23_59524_58_44763() {
    // description: Bakery, 23.59524,58.44763
    val components = mapOf("bakery" to "Muscat Bakery", "city" to "Muscat, Oman",
        "country" to "Oman", "country_code" to "om", "postcode" to "1727", "road" to "Street 3305",
        "state" to "Muscat", "suburb" to "Madinat al Sultan Qaboos")
    val expected = """
        |Muscat Bakery
        |Street 3305
        |1727
        |Muscat, Oman
        |Muscat
        |Oman
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Medical_complex_17_01483_54_09010() {
    // description: Medical complex, 17.01483,54.09010
    val components = mapOf("city" to "Salalah", "clinic" to "Al Zahir Medical Complex",
        "country" to "Oman", "country_code" to "om", "road" to "Al Nahdah Street",
        "state" to "Dhofar")
    val expected = """
        |Al Zahir Medical Complex
        |Al Nahdah Street
        |Salalah
        |Dhofar
        |Oman
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
