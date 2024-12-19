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

public class GN {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Restaurant_in_Conakry_9_51087_13_71186() {
    // description: Restaurant in Conakry, 9.51087,-13.71186
    val components = mapOf("city" to "Kaloum", "country" to "Guinea", "country_code" to "gn", "furniture" to "L'Art de la Table", "road" to "8e Avenue", "state" to "Conakry", "suburb" to "Almamiya 2")
    val expected = """
        |L'Art de la Table
        |8e Avenue
        |Kaloum
        |Conakry
        |Guinea
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}