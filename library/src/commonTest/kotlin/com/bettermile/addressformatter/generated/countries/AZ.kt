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

public class AZ {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Bank_in_Baku_40_37381_49_84436() {
    // description: Bank in Baku, 40.37381,49.84436
    val components = mapOf("bank" to "Bank Avrasiya", "city" to "Baku", "country" to "Azerbaijan", "country_code" to "az", "house_number" to "70", "path" to "Nizami küç.", "postcode" to "AZ1014", "state" to "Bakı İnzibati Ərazisi", "suburb" to "Montin")
    val expected = """
        |Bank Avrasiya
        |70 Nizami küç.
        |AZ1014 Baku
        |Azerbaijan
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}
