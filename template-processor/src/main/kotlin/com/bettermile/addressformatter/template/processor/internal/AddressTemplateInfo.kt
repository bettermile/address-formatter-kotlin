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

package com.bettermile.addressformatter.template.processor.internal

import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSNode

internal class AddressTemplateInfo(
    val name: String,
    val packageName: String,
    val template: String,
    val location: KSNode,
    val file: KSFile,
)
