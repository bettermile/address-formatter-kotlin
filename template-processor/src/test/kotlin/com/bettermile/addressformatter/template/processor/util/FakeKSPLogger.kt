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

package com.bettermile.addressformatter.template.processor.util

import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode

class FakeKSPLogger : KSPLogger {
    private val mutableErrors = mutableListOf<String>()
    val errors: List<String> get() = mutableErrors.toList()

    override fun error(message: String, symbol: KSNode?) {
        mutableErrors.add(message)
    }

    override fun exception(e: Throwable) {
        error("should not be called")
    }

    override fun info(message: String, symbol: KSNode?) {
        error("should not be called")
    }

    override fun logging(message: String, symbol: KSNode?) {
        error("should not be called")
    }

    override fun warn(message: String, symbol: KSNode?) {
        error("should not be called")
    }

}
