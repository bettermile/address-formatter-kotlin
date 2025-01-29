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

import com.bettermile.addressformatter.template.AddressTemplate
import com.google.devtools.ksp.processing.KSPLogger
import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec

internal class AddressFormatterCodeGenerator(private val logger: KSPLogger) {

    private val optInAddressFormatterApiAnnotation = AnnotationSpec.builder(ClassName("kotlin", "OptIn"))
        .addMember(
            "%L::class",
            ClassName("com.bettermile.addressformatter.template", "InternalForInheritanceAddressFormatterApi"),
        ).build()

    internal fun generateFile(
        addressTemplateInfos: List<AddressTemplateInfo>,
        objectName: String = "AddressTemplates",
    ): FileSpec {
        val properties = addressTemplateInfos.mapNotNull {
            AddressTemplateParser(logger, it.location, it.template).generateAddressTemplateTypeSpec()
                ?.let { addressTemplateTypeSpec ->
                    val getter = FunSpec.getterBuilder()
                        .addStatement("return %L", addressTemplateTypeSpec)
                        .build()
                    PropertySpec.builder(it.name, AddressTemplate::class).getter(getter).build()
                }
        }
        val addressTemplatesObjectType = TypeSpec.objectBuilder(objectName)
            .addAnnotation(optInAddressFormatterApiAnnotation)
            .addModifiers(KModifier.INTERNAL)
            .addProperties(properties)
            .build()
        return FileSpec.builder(packageName = addressTemplateInfos.first().packageName, fileName = objectName)
            .addType(addressTemplatesObjectType)
            .build()
    }

    private fun AddressTemplateParser.generateAddressTemplateTypeSpec(): TypeSpec? {
        return parse()?.let { structure ->
            TypeSpec.anonymousClassBuilder()
                .addSuperinterface(AddressTemplate::class)
                .addFunction(
                    FunSpec.builder(AddressTemplate::render.name)
                        .addModifiers(KModifier.OVERRIDE)
                        .addParameter("context", Map::class.parameterizedBy(String::class, String::class))
                        .returns(String::class)
                        .addCode(AddressFormattersRenderer.render(structure))
                        .build()
                )
                .build()
        }
    }
}
