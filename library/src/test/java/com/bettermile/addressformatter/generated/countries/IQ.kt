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

public class IQ {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Supermarket_33_3559_44_4015() {
    // description: Supermarket, 33.3559,44.4015
    val components = mapOf("city" to "Baghdad", "city_district" to "Rusafa", "country" to "Iraq", "country_code" to "iq", "county" to "Al Resafa", "postcode" to "222", "road" to "A86/N11/D383", "state" to "Baghdad", "suburb" to "Mustansiriya", "supermarket" to "al mustansriya Central Market")
    val expected = """
        |al mustansriya Central Market
        |Rusafa
        |A86/N11/D383
        |Baghdad
        |222
        |Iraq
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Supermarket_language_ar_33_3559_44_4015() {
    // description: Supermarket, language=ar, 33.3559,44.4015
    val components = mapOf("city" to "بغداد", "city_district" to "Rusafa", "country" to "العراق", "country_code" to "iq", "county" to "Al Resafa", "postcode" to "222", "road" to "A86/N11/D383", "state" to "محافظة بغداد", "suburb" to "Mustansiriya", "supermarket" to "al mustansriya Central Market")
    val expected = """
        |al mustansriya Central Market
        |Rusafa
        |A86/N11/D383
        |بغداد
        |222
        |العراق
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Hospital_36_32326_43_12339() {
    // description: Hospital, 36.32326,43.12339
    val components = mapOf("city" to "Mosul", "country" to "Iraq", "country_code" to "iq", "county" to "Al Mnsul Qadha", "hospital" to "Mosul General Hospital", "road" to "الجسر الرابع", "state" to "Nineveh", "suburb" to "وادي حجر")
    val expected = """
        |Mosul General Hospital
        |وادي حجر
        |الجسر الرابع
        |Mosul
        |Iraq
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun Town_hall_33_23623_44_31577() {
    // description: Town hall, 33.23623,44.31577
    val components = mapOf("city" to "Baghdad", "city_district" to "Rasheed", "country" to "Iraq", "country_code" to "iq", "county" to "Karkh", "road" to "شارع قطر الندى", "state" to "Baghdad", "suburb" to "Resala", "townhall" to "Masgd")
    val expected = """
        |Masgd
        |Rasheed
        |شارع قطر الندى
        |Baghdad
        |Iraq
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun `36_19137_43_97445`() {
    // description: 36.19137, 43.97445
    val components = mapOf("city" to "Arbil", "continent" to "Asia", "country" to "Iraq", "country_code" to "iq", "county" to "قضاء أربيل", "house_number" to "391", "neighbourhood" to "English Village", "postcode" to "44001", "state" to "هەرێمی کوردستان", "suburb" to "گوندی ئینگلیزی")
    val expected = """
        |391 English Village
        |Arbil
        |44001
        |Iraq
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}