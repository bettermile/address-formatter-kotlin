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

public class MQ {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Administrative_building_in_Fort_au_France_14_60443_61_07770() {
    // description: Administrative building in Fort-au-France, 14.60443,-61.07770
    val components = mapOf("city" to "Fort-de-France", "country" to "Martinique", "country_code" to "mq", "county" to "Fort-de-France", "courthouse" to "Tribunal Administratif de Fort-de-France", "postcode" to "97200", "road" to "Avenue Condorcet", "state" to "Martinique")
    val expected = """
        |Tribunal Administratif de Fort-de-France
        |Avenue Condorcet
        |97200 Fort-de-France
        |Martinique, France
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}