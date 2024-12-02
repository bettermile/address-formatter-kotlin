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

public class RO {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun building_in_Cluj_46_77498_23_59376() {
    // description: building in Cluj - 46.77498,23.59376
    val components = mapOf("city" to "Cluj-Napoca", "country" to "Romania", "country_code" to "ro", "county" to "Cluj-Napoca", "house_number" to "6", "postcode" to "400157", "road" to "Strada Ploiești", "state" to "Cluj", "suburb" to "Gruia")
    val expected = """
        |Strada Ploiești 6
        |400157 Cluj-Napoca
        |Romania
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
