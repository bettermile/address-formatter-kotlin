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

public class PF {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Office_in_Papeete_17_53714_149_56608() {
    // description: Office in Papeete, -17.53714,-149.56608
    val components = mapOf("building" to "Mairie (bureaux administratifs)", "city" to "Papeete",
        "country" to "Polynésie française, Îles du Vent (eaux territoriales)",
        "country_code" to "pf", "county" to "Îles du Vent", "postcode" to "98714",
        "road" to "Rue des Remparts", "state" to "French Polynesia")
    val expected = """
        |Mairie (bureaux administratifs)
        |Rue des Remparts
        |98714 Papeete
        |Polynésie française, France
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
