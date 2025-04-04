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

public class YE {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Shop_15_28562_44_23028() {
    // description: Shop, 15.28562,44.23028
    val components = mapOf("city" to "Sanʿaʾ", "country" to "Yemen", "country_code" to "ye",
        "county" to "Qada Sana", "mall" to "Yassin Spices", "road" to "شارع الحربي")
    val expected = """
        |Yassin Spices
        |شارع الحربي
        |Sanʿaʾ
        |Yemen
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Shop_language_ar_15_28562_44_23028() {
    // description: Shop, language=ar, 15.28562,44.23028
    val components = mapOf("city" to "صنعاء", "country" to "اليمن", "country_code" to "ye",
        "county" to "قضاء صنعاء", "mall" to "بهارات ياسين", "road" to "شارع الحربي")
    val expected = """
        |بهارات ياسين
        |شارع الحربي
        |صنعاء
        |اليمن
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Central_bank_15_34924_44_20724() {
    // description: Central bank, 15.34924,44.20724
    val components = mapOf("bank" to "Centeral Bank of Yemen", "city" to "Sanʿaʾ",
        "country" to "Yemen", "country_code" to "ye", "county" to "Qada Sana", "postcode" to "01",
        "road" to "Al Zubayri Street")
    val expected = """
        |Centeral Bank of Yemen
        |Al Zubayri Street
        |Sanʿaʾ
        |Yemen
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Pizzaria_12_77954_45_04418() {
    // description: Pizzaria, 12.77954,45.04418
    val components = mapOf("city" to "Aden", "country" to "Yemen", "country_code" to "ye",
        "county" to "Aden Directorate", "fast_food" to "Pizza", "residential" to "كريتر",
        "road" to "خط صيرة", "state" to "‘Adan Governorate")
    val expected = """
        |Pizza
        |خط صيرة
        |Aden
        |Yemen
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
