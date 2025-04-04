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

public class CM {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun UN_building_in_Yaound_3_85890_11_51580() {
    // description: UN building in Yaoundé, 3.85890,11.51580
    val components = mapOf("building" to "ONUDI", "city" to "Yaoundé III", "country" to "Cameroon",
        "country_code" to "cm", "county" to "CUY", "postcode" to "11852", "road" to "rue 3.025",
        "state" to "Centre", "suburb" to "Centre Administratif")
    val expected = """
        |ONUDI
        |rue 3.025
        |Yaoundé III
        |Cameroon
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
