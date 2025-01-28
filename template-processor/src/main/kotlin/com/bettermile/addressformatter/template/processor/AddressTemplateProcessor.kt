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

package com.bettermile.addressformatter.template.processor

import com.bettermile.addressformatter.template.AddressTemplateDefinition
import com.bettermile.addressformatter.template.processor.internal.AddressFormatterCodeGenerator
import com.bettermile.addressformatter.template.processor.internal.AddressTemplateInfo
import com.bettermile.addressformatter.template.processor.internal.getAnnotationsAndNodes
import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.containingFile
import com.google.devtools.ksp.processing.CodeGenerator
import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSDeclaration
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.ksp.writeTo

class AddressTemplateProcessor(
    private val logger: KSPLogger,
    private val codeGenerator: CodeGenerator,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {
        val addressFormatterCodeGenerator = AddressFormatterCodeGenerator(logger)
        val sequenceOfAnnotated =
            resolver.getSymbolsWithAnnotation(checkNotNull(AddressTemplateDefinition::class.qualifiedName))
        val packages = sequenceOfAnnotated.flatMap { property ->
            addressTemplates(property)
        }.groupBy(AddressTemplateInfo::packageName)
        packages.forEach { (_, templateInfoList) ->
            if (!templateInfoList.hasDuplicatedNames()) {
                val dependencyFiles = templateInfoList.map(AddressTemplateInfo::file).distinct()
                val fileSpec: FileSpec = addressFormatterCodeGenerator.generateFile(templateInfoList)
                fileSpec.writeTo(codeGenerator, Dependencies(true, sources = dependencyFiles.toTypedArray()))
            }
        }

        return emptyList()
    }

    private fun List<AddressTemplateInfo>.hasDuplicatedNames(): Boolean {
        var foundError = false
        groupBy(AddressTemplateInfo::name).forEach { (name, templateInfoListWithSameName) ->
            if (templateInfoListWithSameName.size > 1) {
                templateInfoListWithSameName.forEach {
                    logger.error(
                        message = "Found multiple AddressTemplateDefinition annotations " +
                                "with the same property name ($name)",
                        symbol = it.location,
                    )
                    foundError = true
                }
            }
        }
        return foundError
    }

    private fun addressTemplates(property: KSAnnotated): Sequence<AddressTemplateInfo> {
        val containingFile = property.containingFile
        val packageName = containingFile?.packageName?.asString()
        if (packageName == null) {
            logger.error("no package found", property)
            return emptySequence()
        }
        val propertyName = (property as? KSDeclaration)?.simpleName?.getShortName()
        @OptIn(KspExperimental::class)
        return property.getAnnotationsAndNodes<AddressTemplateDefinition>()
            .mapNotNull { (node, addressTemplateDefinition) ->
                val template = addressTemplateDefinition.value
                val name = addressTemplateDefinition.propertyName.takeIf(String::isNotEmpty) ?: propertyName
                if (name == null) {
                    logger.error("no property name found, please specify one explicitly", node)
                    null
                } else {
                    AddressTemplateInfo(
                        name = name,
                        packageName = packageName,
                        template = template,
                        location = node,
                        file = containingFile,
                    )
                }
            }
    }
}
