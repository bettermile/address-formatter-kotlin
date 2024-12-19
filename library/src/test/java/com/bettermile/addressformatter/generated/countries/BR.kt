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

public class BR {
  private val addressFormatter: AddressFormatter =
      AddressFormatter(abbreviate = false, appendCountry = false)

  @Test
  public fun Rio() {
    // description: Rio
    val components = mapOf("city" to "Rio de Janeiro", "country" to "Brazil", "country_code" to "BR", "county" to "Rio de Janeiro", "road" to "Avenida Maracanã", "state" to "RJ", "state_district" to "Região Metropolitana do Rio de Janeiro", "suburb" to "Maracanã")
    val expected = """
        |Avenida Maracanã
        |Maracanã
        |Rio de Janeiro - RJ
        |Brazil
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun format_postcode_correctly() {
    // description: format postcode correctly
    val components = mapOf("city" to "Belo Horizonte", "city_district" to "Regional Centro-Sul", "country" to "Brazil", "country_code" to "br", "county" to "Microrregião Belo Horizonte", "neighbourhood" to "Centro", "postcode" to "30170011", "road" to "Rua Rio de Janiero", "state" to "Minas Gerais", "state_district" to "Mesorregião Metropolitana de Belo Horizonte", "suburb" to "Centro")
    val expected = """
        |Rua Rio de Janiero
        |Centro
        |Belo Horizonte - MG
        |30170-011
        |Brazil
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun BR_housenumber_22_92540_43_17540() {
    // description: BR housenumber -22.92540,-43.17540
    val components = mapOf("city" to "Rio de Janeiro", "city_district" to "Zona Central do Rio de Janeiro", "continent" to "South America", "country" to "Brazil", "country_code" to "br", "county" to "Região Geográfica Imediata do Rio de Janeiro", "house" to "Condomínio Condomínio René Magritte", "house_number" to "68", "postcode" to "22221-000", "road" to "Rua Silveira Martins", "state" to "Rio de Janeiro", "state_code" to "RJ", "state_district" to "Região Geográfica Intermediária do Rio de Janeiro", "suburb" to "Catete")
    val expected = """
        |Condomínio Condomínio René Magritte
        |Rua Silveira Martins 68
        |Catete
        |Rio de Janeiro - RJ
        |22221-000
        |Brazil
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }

  @Test
  public fun village() {
    // description: village
    val components = mapOf("country" to "Brazil", "country_code" to "br", "municipality" to "Região Geográfica Imediata de Picos", "postcode" to "64675-000", "region" to "Northeast Region", "state" to "Piauí", "state_code" to "PI", "state_district" to "Região Geográfica Intermediária de Picos", "village" to "Alegrete do Piauí")
    val expected = """
        |Alegrete do Piauí
        |Região Geográfica Intermediária de Picos - PI
        |64675-000
        |Brazil
        |""".trimMargin()
    val actual = addressFormatter.format(components = components)
    assertEquals(expected, actual)
  }
}