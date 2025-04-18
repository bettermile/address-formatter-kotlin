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

public class VG {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Govt_building_in_Road_Town_18_42132_64_61601() {
    // description: Govt building in Road Town, 18.42132,-64.61601
    val components = mapOf("building" to "Government of the BVI",
        "country" to "British Virgin Islands", "country_code" to "vg", "house_number" to "33",
        "island" to "Tortola", "postcode" to "VG1110", "road" to "Admin Drive",
        "town" to "Road Town")
    val expected = """
        |Government of the BVI
        |33 Admin Drive
        |Road Town, Tortola
        |British Virgin Islands, VG1110
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
