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

public class PL {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun road_address_at_51_262986_15_568984() {
    // description: road address at 51.262986,15.568984
    val components = mapOf("house_number" to "1", "road" to "Adama Asnyka",
        "neighbourhood" to "Południe", "city" to "Bolesławiec", "postcode" to "59-700",
        "county" to "Bolesławiec", "state" to "Lower Silesian Voivodeship", "country" to "Poland",
        "country_code" to "PL")
    val expected = """
        |Adama Asnyka 1
        |59-700 Bolesławiec
        |Poland
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun exclude_garbage_input_50_01009_20_99881() {
    // description: exclude garbage input, 50.01009,20.99881
    val components = mapOf("city" to "Tarnów", "country" to "Poland", "country_code" to "pl",
        "county" to "Tarnów",
        "fuel" to "http://ump.waw.pl/ retrieved 11:18:52 11/17/09 (UMP-Tarnow/src/POI-paliwo.pnt-converted.txt)",
        "neighbourhood" to "XXVI Lecia", "postcode" to "33-106", "road" to "Mostowa",
        "state" to "Lesser Poland Voivodeship")
    val expected = """
        |Mostowa
        |33-106 Tarnów
        |Poland
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun ensure_postcode_well_formatted_53_43888_14_57544() {
    // description: ensure postcode well formatted, 53.43888,14.57544
    val components = mapOf("city" to "Szczecin", "city_district" to "Śródmieście",
        "country" to "Poland", "country_code" to "pl", "county" to "Szczecin",
        "house_number" to "19", "neighbourhood" to "Grabowo", "postcode" to "71637",
        "road" to "Teofila Firlika", "state" to "West Pomeranian Voivodeship",
        "suburb" to "Drzetowo-Grabowo")
    val expected = """
        |Teofila Firlika 19
        |71-637 Szczecin
        |Poland
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
