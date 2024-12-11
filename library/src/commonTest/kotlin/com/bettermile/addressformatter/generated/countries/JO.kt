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

public class JO {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Gallery_31_95791_35_91522() {
    // description: Gallery, 31.95791,35.91522
    val components = mapOf("city" to "Amman", "country" to "Jordan", "country_code" to "jo", "museum" to "Jordan National Gallery of Fine Arts, Building 1", "postcode" to "11190", "road" to "Husni Fareez Street", "state" to "Amman")
    val expected = """
        |Jordan National Gallery of Fine Arts, Building 1
        |Husni Fareez Street
        |11190 Amman
        |Jordan
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Supermarket_31_95101_35_91891() {
    // description: Supermarket, 31.95101,35.91891
    val components = mapOf("city" to "Amman", "country" to "Jordan", "country_code" to "jo", "postcode" to "11183", "road" to "Abu Al Wafa Al Dajani Street", "state" to "Amman", "supermarket" to "هَبوب - Haboob")
    val expected = """
        |هَبوب - Haboob
        |Abu Al Wafa Al Dajani Street
        |11183 Amman
        |Jordan
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Supermarket_language_ar_31_95101_35_91891() {
    // description: Supermarket, language=ar, 31.95101,35.91891
    val components = mapOf("city" to "عمان", "country" to "الأردن", "country_code" to "jo", "postcode" to "11183", "road" to "Abu Al Wafa Al Dajani Street", "state" to "‏محافظة العاصمة‎", "supermarket" to "هَبوب")
    val expected = """
        |هَبوب
        |Abu Al Wafa Al Dajani Street
        |11183 عمان
        |الأردن
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Dental_clinic_31_91740_35_89150() {
    // description: Dental clinic, 31.91740,35.89150
    val components = mapOf("building" to "Khalifeh Mall", "city" to "Amman", "country" to "Jordan", "country_code" to "jo", "postcode" to "11183", "road" to "جبل عرفات", "state" to "Amman", "suburb" to "Sweifieh")
    val expected = """
        |Khalifeh Mall
        |جبل عرفات
        |11183 Amman
        |Jordan
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Dental_clinic_language_ar_31_91740_35_89150() {
    // description: Dental clinic, language=ar, 31.91740,35.89150
    val components = mapOf("building" to "خليفة مول", "city" to "عمان", "country" to "الأردن", "country_code" to "jo", "postcode" to "11183", "road" to "جبل عرفات", "state" to "‏محافظة العاصمة‎", "suburb" to "حي الصويفية")
    val expected = """
        |خليفة مول
        |جبل عرفات
        |11183 عمان
        |الأردن
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
