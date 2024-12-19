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

public class MK {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun School_in_Skopje_41_9891_21_4406() {
    // description: School in Skopje, 41.9891,21.4406
    val components = mapOf("city" to "Skopje", "country" to "F.Y.R.O.M.", "country_code" to "mk", "county" to "Municipality of Centar", "postcode" to "1000", "road" to "Мирче Ацев", "school" to "ОУ 11-ти Октомври", "state" to "Skopje Region", "suburb" to "Prolet")
    val expected = """
        |ОУ 11-ти Октомври
        |Мирче Ацев
        |1000 Skopje
        |F.Y.R.O.M.
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}