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

public class TN {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun main_theatre_in_Tunis_36_79900_10_18083() {
    // description: main theatre in Tunis 36.79900,10.18083
    val components = mapOf("city" to "Tunis", "country" to "Tunisia", "country_code" to "tn", "postcode" to "1000", "road" to "Rue de Grèce نهج اليونان", "state" to "Tunis", "state_district" to "Bab Bhar", "theatre" to "Théâtre Municipal de Tunis المسرح البلدي بتونس")
    val expected = """
        |Théâtre Municipal de Tunis المسرح البلدي بتونس
        |Rue de Grèce نهج اليونان
        |1000 Tunis
        |Tunisia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun cinema_in_Sousse_35_83053_10_64063() {
    // description: cinema in Sousse 35.83053,10.64063
    val components = mapOf("cinema" to "Cinéma Palace", "city" to "Sousse", "country" to "Tunisia", "country_code" to "tn", "postcode" to "4000", "road" to "Rue Pasteur", "state" to "Sousse", "suburb" to "Sousse Riadh")
    val expected = """
        |Cinéma Palace
        |Rue Pasteur
        |4000 Sousse
        |Tunisia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun hospital_in_Sfax_34_7411_10_7590() {
    // description: hospital in Sfax 34.7411,10.7590
    val components = mapOf("city" to "صفاقس", "country" to "Tunisia", "country_code" to "tn", "county" to "Sfax Medina", "hospital" to "CNSS", "postcode" to "3000", "road" to "RN 1 طو", "state" to "Sfax", "suburb" to "صفاقس الجديدة")
    val expected = """
        |CNSS
        |RN 1 طو
        |3000 صفاقس
        |Tunisia
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}