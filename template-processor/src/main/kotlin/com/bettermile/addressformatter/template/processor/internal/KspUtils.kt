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

import com.bettermile.addressformatter.template.AddressTemplateDefinition
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation

internal fun KSAnnotated.getAnnotationsAndNodes(): Sequence<Pair<KSAnnotation, AddressTemplateDefinition>> {
    val clazz = AddressTemplateDefinition::class
    // copied from getAnnotationsByType implementation
    return annotations.filter { annotationNode ->
        annotationNode.shortName.getShortName() == clazz.simpleName &&
                annotationNode.annotationType.resolve().declaration.qualifiedName?.asString() == clazz.qualifiedName
    }.map { annotationNode ->
        val value = annotationNode.argumentValue<String>(AddressTemplateDefinition::value.name)
        val propertyName = annotationNode.argumentValue<String>(AddressTemplateDefinition::propertyName.name, "")
        annotationNode to AddressTemplateDefinition(value, propertyName)
    }
}

private inline fun <reified T : Any> KSAnnotation.argumentValue(name: String, default: T? = null): T {
    return arguments.firstOrNull { it.name?.asString() == name }?.value as T?
        ?: defaultArguments.firstOrNull { it.name?.asString() == name }?.value as T?
        ?: checkNotNull(default)
}
