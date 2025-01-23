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

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.buildCodeBlock

internal object AddressFormattersRenderer {

    fun render(structure: List<AddressTemplateStructure>): CodeBlock {
        return buildCodeBlock {
            beginControlFlow("return buildString")
            structure.forEach {
                when (it) {
                    is AddressTemplateStructure.FirstLambda -> appendFirstLambda(it)
                    is AddressTemplateStructure.Simple.Literal -> appendLiteral(it)
                    is AddressTemplateStructure.Simple.Placeholder -> appendPlaceholder(it)
                }
            }
            endControlFlow()
        }
    }

    private fun CodeBlock.Builder.appendLiteral(literal: AddressTemplateStructure.Simple.Literal) {
        var text = literal.text
        if (text.length == 1) {
            if (text == "\n") text = "\\n"
            addStatement("append('%L')", text)
        } else {
            addStatement("append(\"%L\")", text.replace("\n", "\\n"))
        }
    }

    private fun CodeBlock.Builder.appendPlaceholder(placeholder: AddressTemplateStructure.Simple.Placeholder) {
        addStatement("context[%S]?.also(::append)", placeholder.name)
    }

    private fun CodeBlock.Builder.appendFirstLambda(lambda: AddressTemplateStructure.FirstLambda) {
        beginControlFlow("sequence")
        lambda.elements.forEach { elements ->
            val element = elements.joinToString(separator = "") {
                when (it) {
                    is AddressTemplateStructure.Simple.Literal -> it.text
                    is AddressTemplateStructure.Simple.Placeholder -> "\${context[\"${it.name}\"] ?: \"\"}"
                }
            }
            addStatement("yield(%P)", element)
        }
        endControlFlow()
        addStatement(".firstOrNull(String::isNotBlank)?.also(::append)")
    }
}
