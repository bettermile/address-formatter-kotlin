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

package com.bettermile.addressformatter.template

/**
 *
 * Generates an [AddressTemplate] in a `AddressFormatters` object with the given [propertyName]. The address template
 * contains of literal text, placeholders for address components and an option to choose the first not empty component.
 * It's a subset of [mustache specification](https://mustache.github.io/).
 *
 * Placeholders are surrounded by 3 curly braces
 * ```
 * {{{road}}}
 * ```
 *
 * If there are multiple components which could fit at one place, you can use the `first` lambda. It splits the text
 * between `{{#first}}` and `{{/first}}` at `||`, replaces the placeholders with the components and checks for the first
 * element, that's not blank or uses an empty string, if all elements are blank.
 * ```
 * {{#first}} {{{road}}} || {{{place}}} || {{{hamlet}}} {{/first}}
 * ```
 *
 * @param value address template used to render addresses
 * @param propertyName name of the property to hold the information. Defaults to the name of the target property.
 */
@Repeatable
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class AddressTemplateDefinition(val value: String, val propertyName: String = "")
