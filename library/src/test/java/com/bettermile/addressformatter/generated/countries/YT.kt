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

public class YT {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Government_building_in_Mamoudzou_12_77958_45_23145() {
    // description: Government building in Mamoudzou, -12.77958,45.23145
    val components = mapOf("building" to "Conseil Général de Mayotte", "country" to "France, Mayotte (eaux territoriales)", "country_code" to "yt", "postcode" to "97600", "road" to "Rue Houmadi Bacar", "state" to "Mayotte", "town" to "Mamoudzou")
    val expected = """
        |Conseil Général de Mayotte
        |Rue Houmadi Bacar
        |97600 Mamoudzou
        |Mayotte, France
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}