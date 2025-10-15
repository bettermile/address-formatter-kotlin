/*
 * Copyright (c) 2020 Pirbright Software
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

internal class RegexPatternCache {
    private val map: MutableMap<RegexMapKey, Regex> = hashMapOf()

    operator fun get(
        key: String,
        vararg regexOptions: RegexOption,
    ): Regex {
        val mapKey = RegexMapKey(key, regexOptions.toHashSet())
        return map[mapKey] ?: Regex(key, mapKey.regexOptions).also { map[mapKey] = it }
    }

    private data class RegexMapKey(val key: String, val regexOptions: Set<RegexOption>)
}
