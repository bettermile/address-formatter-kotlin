/*
 * Copyright 2022 GLS eCom Lab GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bettermile.addressformatter

internal data class CountryFormat(
    val addressTemplate: String? = null,
    val fallbackTemplate: String? = null,
    val replace: List<Replace> = emptyList(),
    val postformatReplace: List<Replace> = emptyList(),
    val useCountry: String? = null,
    val changeCountry: String? = null,
    val addComponent: String? = null,
) {
    data class Replace(
        val search: String,
        val replacement: String,
    )
}
