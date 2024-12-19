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

public class MF {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Post_office_in_Marigot_18_06671_63_08566() {
    // description: Post office in Marigot, 18.06671,-63.08566
    val components = mapOf("country" to "Saint-Martin", "country_code" to "mf", "post_office" to "Bureau de Poste", "postcode" to "97150", "road" to "Rue de La Poste", "state" to "Saint-Martin", "suburb" to "Concordia", "town" to "Marigot")
    val expected = """
        |Bureau de Poste
        |Rue de La Poste
        |97150 Marigot
        |France
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}