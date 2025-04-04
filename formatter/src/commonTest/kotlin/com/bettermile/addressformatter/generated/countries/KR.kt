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

public class KR {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Hotel_in_Gangnam_Seoul_37_49675_127_02755() {
    // description: Hotel in Gangnam, Seoul, 37.49675,127.02755
    val components = mapOf("city" to "Seoul", "city_district" to "서초2동 (Seocho2-dong)",
        "country" to "South Korea", "country_code" to "kr", "hotel" to "Haeundae Grand Hotel",
        "postcode" to "135-934", "road" to "Seocho-daero 74-gil", "town" to "Seocho-gu",
        "village" to "Seocho-dong")
    val expected = """
        |South Korea
        |Seoul 서초2동 (Seocho2-dong) Seocho-daero 74-gil
        |Haeundae Grand Hotel
        |135-934
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun district_of_Seoul() {
    // description: district of Seoul
    val components = mapOf("city" to "Seoul", "country" to "South Korea", "country_code" to "kr",
        "city_district" to "Gangnam-gu")
    val expected = """
        |Gangnam-gu
        |Seoul
        |South Korea
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
