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

public class TW {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Library_in_25_02511_121_46330() {
    // description: Library in 新北市板橋區, 25.02511,121.46330
    val components = mapOf("city" to "新北市板橋區", "country" to "中華民國", "country_code" to "tw", "house_number" to "168", "library" to "新北市立圖書館板橋四維分館", "postcode" to "220", "road" to "陽明街", "state" to "台中台北各種語言名稱", "suburb" to "新北板橋土城多語言")
    val expected = """
        |中華民國
        |220
        |新北市板橋區 新北板橋土城多語言 陽明街 168
        |新北市立圖書館板橋四維分館
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}