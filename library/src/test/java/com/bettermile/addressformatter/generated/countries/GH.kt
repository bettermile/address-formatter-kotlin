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

public class GH {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Accra_5_56008_0_20236() {
    // description: Bank in Accra, 5.56008,-0.20236
    val components = mapOf("building" to "Ecobank", "city" to "Accra", "country" to "Ghana", "country_code" to "gh", "county" to "Accra Metropolitan", "postcode" to "233", "road" to "7th Avenue", "state" to "Greater Accra Region", "suburb" to "West Ridge")
    val expected = """
        |Ecobank
        |7th Avenue
        |Accra
        |Ghana
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}