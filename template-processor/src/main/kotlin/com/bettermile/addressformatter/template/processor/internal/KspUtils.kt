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

import com.google.devtools.ksp.KspExperimental
import com.google.devtools.ksp.getAnnotationsByType
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSAnnotation

@KspExperimental
internal inline fun <reified T : Annotation> KSAnnotated.getAnnotationsAndNodes(): Sequence<Pair<KSAnnotation, T>> {
    val clazz = T::class
    // copied from getAnnotationsByType implementation
    return annotations.filter {
        it.shortName.getShortName() == clazz.simpleName &&
                it.annotationType.resolve().declaration.qualifiedName?.asString() == clazz.qualifiedName
    }.zipSameSize(getAnnotationsByType(clazz))
}

fun <T, R> Sequence<T>.zipSameSize(other: Sequence<R>): Sequence<Pair<T, R>> {
    val iterator1 = this.iterator()
    val iterator2 = other.iterator()

    return sequence {
        while (iterator1.hasNext() && iterator2.hasNext()) yield(iterator1.next() to iterator2.next())
        require(!iterator1.hasNext() && !iterator2.hasNext()) { "Sequences have different sizes" }
    }
}
