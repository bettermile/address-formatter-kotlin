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

public class BA {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Post_office_in_Sarajevo_43_85229_18_38142() {
    // description: Post office in Sarajevo, 43.85229,18.38142
    val components = mapOf("city" to "Sarajevo", "country" to "Bosnia and Herzegovina", "country_code" to "ba", "county" to "New Sarajevo municipality", "house_number" to "88", "post_office" to "BH Posta", "postcode" to "71000", "road" to "Zmaja od Bosne", "state" to "Entity Federation of Bosnia and Herzegovina", "state_district" to "Sarajevo Canton", "suburb" to "Grbavica")
    val expected = """
        |BH Posta
        |Zmaja od Bosne 88
        |71000 Sarajevo
        |Bosnia and Herzegovina
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
