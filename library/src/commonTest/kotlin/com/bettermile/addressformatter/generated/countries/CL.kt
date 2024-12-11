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

public class CL {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun _33_42983_70_60620() {
    // description: -33.42983,-70.60620
    val components = mapOf("city" to "Providencia", "country" to "Chile", "country_code" to "cl", "county" to "Provincia de Santiago", "house_number" to "920", "neighbourhood" to "Unidad Vecinal Providencia", "postcode" to "7500000", "region" to "Región Metropolitana de Santiago", "road" to "Avenida Ricardo Lyon", "state" to "XIII Región Metropolitana de Santiago", "suburb" to "Providencia")
    val expected = """
        |Avenida Ricardo Lyon 920
        |7500000 Providencia
        |Región Metropolitana de Santiago
        |Chile
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
