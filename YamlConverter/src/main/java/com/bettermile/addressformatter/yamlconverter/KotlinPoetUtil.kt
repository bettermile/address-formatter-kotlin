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

package com.bettermile.addressformatter.yamlconverter

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.buildCodeBlock
import com.squareup.kotlinpoet.joinToCode
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun generatedFileSpec(
    fileName: String,
    packageName: String = "com.bettermile.addressformatter.generated",
    block: FileSpec.Builder.() -> Unit,
): FileSpec {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return FileSpec.builder(packageName = packageName, fileName = fileName)
        .addFileComment(
            """
            Copyright 2022 GLS eCom Lab GmbH
           
            Licensed under the Apache License, Version 2.0 (the "License");
            you may not use this file except in compliance with the License.
            You may obtain a copy of the License at
           
                http://www.apache.org/licenses/LICENSE-2.0
           
            Unless required by applicable law or agreed to in writing, software
            distributed under the License is distributed on an "AS IS" BASIS,
            WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
            See the License for the specific language governing permissions and
            limitations under the License.
            
            
            """.trimIndent()
        )
        .addFileComment("THIS FILE IS AUTOGENERATED, PLEASE DON'T CHANGE IT MANUALLY")
        .apply(block)
        .build()
}

fun multilineFunctionCall(functionName: String, elements: List<CodeBlock>): CodeBlock {
    return buildCodeBlock {
        add("$functionName(")
        indentParameters(elements)
    }
}

fun multilineFunctionCall(functionName: TypeName, elements: List<CodeBlock>): CodeBlock {
    return buildCodeBlock {
        add("%T(", functionName)
        indentParameters(elements)
    }
}

private fun CodeBlock.Builder.indentParameters(elements: List<CodeBlock>) {
    indent()
    add(elements.joinToCode(separator = ",\n", prefix = "\n", suffix = ",\n"))
    unindent()
    add(")")
}