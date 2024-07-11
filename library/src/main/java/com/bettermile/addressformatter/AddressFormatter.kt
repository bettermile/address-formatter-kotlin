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

import com.bettermile.addressformatter.generated.Worldwide
import com.bettermile.addressformatter.generated.abbreviations
import com.bettermile.addressformatter.generated.aliases
import com.bettermile.addressformatter.generated.country2Languages
import com.bettermile.addressformatter.generated.countryNames
import com.bettermile.addressformatter.generated.countyCodes
import com.bettermile.addressformatter.generated.stateCodes
import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.MustacheFactory
import java.io.StringReader
import java.io.StringWriter
import java.util.function.Function
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Formatter for addresses. The address format [String]s come from the
 * [OpenCage repository](https://github.com/OpenCageData/address-formatting/).
 *
 * Example:
 * ```kotlin
 * val formatter = AddressFormatter(abbreviate = false, appendCountry = false)
 * val components = mapOf(
 *     "country_code" to "US",
 *     "house_number" to "301",
 *     "road" to "Hamilton Avenue",
 *     "neighbourhood" to "Crescent Park",
 *     "city" to "Palo Alto",
 *     "postcode" to "94303",
 *     "county" to "Santa Clara County",
 *     "state" to "California",
 *     "country" to "United States",
 * )
 * println(formatter.format(components))
 * /*
 * 301 Hamilton Avenue
 * Palo Alto, CA 94303
 * United States of America
 * */
 * ```
 *
 * @param abbreviate use abbreviation rules to abbreviate components depending on the country code of the address
 * @param appendCountry
 *  add the country name to the components depending on the country code, when it's not already in it
 */
class AddressFormatter(
    private val abbreviate: Boolean,
    private val appendCountry: Boolean,
) {

    /**
     * Formats the [components] to an address. The formatting [String] will be inferred by the `country_code` value in
     * the [components] [Map]. If this can't be found, the [fallbackCountryCode] will be used. If this is `null` or no
     * supported country code, the default formatting [String] will be used.
     *
     * @param components address components that are used in the formatted address
     * @param fallbackCountryCode used, when no country code could be found in [components]
     */
    @JvmOverloads
    fun format(components: Map<String, Any>, fallbackCountryCode: String? = null): String {
        var mutableComponents: MutableMap<String, String> =
            components.mapValuesTo(mutableMapOf()) { it.value.toString() }
        mutableComponents = mutableComponents.normalizeFields()
        mutableComponents = determineCountryCode(mutableComponents, fallbackCountryCode)
        val countryCode = requireNotNull(mutableComponents["country_code"])
        if (
            appendCountry &&
            countryNames.containsKey(countryCode) &&
            "country" !in mutableComponents
        ) {
            mutableComponents["country"] = countryNames.getValue(countryCode)
        }
        mutableComponents = applyAliases(mutableComponents)
        val template = findTemplate(mutableComponents)
        mutableComponents = cleanupInput(mutableComponents, template.replace)
        return renderTemplate(template, mutableComponents)
    }

    private fun Map<String, String>.normalizeFields(): MutableMap<String, String> {
        val normalizedComponents: MutableMap<String, String> = hashMapOf()
        for ((field, value) in this) {
            normalizedComponents.putIfAbsent(field.normalizeFieldName(), value)
        }
        return normalizedComponents
    }

    private fun String.normalizeFieldName(): String =
        replace(uppercaseRegex) { "_${it.value.lowercase()}" }

    private fun determineCountryCode(
        components: MutableMap<String, String>,
        fallbackCountryCode: String?
    ): MutableMap<String, String> {
        var countryCode = components["country_code"]
        if (invalidCountryCode(countryCode)) {
            countryCode = fallbackCountryCode?.takeUnless(::invalidCountryCode) ?: "default"
        }

        countryCode = countryCode.uppercase()
        if (countryCode == "UK") {
            countryCode = "GB"
        }
        val country = Worldwide.countries[countryCode]?.value
        if (country?.useCountry != null) {
            val oldCountryCode: String = countryCode
            countryCode = country.useCountry.uppercase()
            if (country.changeCountry != null) {
                var newCountry = country.changeCountry
                val regex = regexPatternCache["\\$(\\w*)"]
                newCountry = regex.replace(newCountry) { matchResult ->
                    val match = matchResult.groupValues[1]
                    components[match] ?: ""
                }
                components["country"] = newCountry
            }
            val oldCountry = Worldwide.countries[oldCountryCode]?.value
            val completeText = oldCountry?.addComponent?.takeIf(String::isNotEmpty)
            if (completeText != null) {
                val assignIndex = completeText.indexOf('=')
                if (assignIndex > 0 && "state" == completeText.substring(0, assignIndex)) {
                    components["state"] = completeText.substring(assignIndex + 1)
                }
            }
        }
        val state = components["state"]
        if (countryCode == "NL" && state != null) {
            if (state == "Curaçao") {
                countryCode = "CW"
                components["country"] = "Curaçao"
            } else if (state.equals("sint maarten", ignoreCase = true)) {
                countryCode = "SX"
                components["country"] = "Sint Maarten"
            } else if (state.equals("aruba", ignoreCase = true)) {
                countryCode = "AW"
                components["country"] = "Aruba"
            }
        }
        components["country_code"] = countryCode
        return components
    }

    @OptIn(ExperimentalContracts::class)
    private fun invalidCountryCode(countryCode: String?): Boolean {
        contract {
            returns(false) implies (countryCode != null)
        }
        return countryCode == null || countryCode.length != 2 ||
                !Worldwide.countries.containsKey(countryCode.uppercase())
    }

    private fun cleanupInput(
        components: MutableMap<String, String>,
        replacements: List<CountryFormat.Replace>
    ): MutableMap<String, String> {
        val country = components["country"]
        val state = components["state"]
        if (country != null && state != null && country.toIntOrNull() != null) {
            components["country"] = state
            components.remove("state")
        }
        if (replacements.isNotEmpty()) {
            for (componentEntry in components.entries) {
                val component = componentEntry.key
                var componentValue = componentEntry.value
                replacements.forEach { replacement ->
                    val replacementText = replacement.search
                    componentValue = if (replacementText.startsWith("$component=")) {
                        val value = replacementText.substring(component.length + 1)
                        componentValue.replace(regexPatternCache[value], replacement.replacement)
                    } else {
                        val regex = regexPatternCache[replacementText]
                        regex.replace(componentValue, replacement.replacement)
                    }
                    componentEntry.setValue(componentValue)
                }
            }
        }
        val stateValue = components["state"]
        if (!components.containsKey("state_code") && stateValue != null) {
            val stateCode = getStateCode(stateValue, requireNotNull(components["country_code"]))
            if (stateCode != null) {
                components["state_code"] = stateCode
            }
            val p = regexPatternCache["^washington,? d\\.?c\\.?", RegexOption.IGNORE_CASE]
            if (p.containsMatchIn(stateValue)) {
                components["state_code"] = "DC"
                components["state"] = "District of Columbia"
                components["city"] = "Washington"
            }
        }
        val county = components["county"]
        if (!components.containsKey("county_code") && county != null) {
            val countyCode = getCountyCode(county, requireNotNull(components["country_code"]))
            if (countyCode != null) {
                components["county_code"] = countyCode
            }
        }
        val unknownComponents =
            components.asSequence().filter { (key, _) -> key !in aliases.keys }
                .map(Map.Entry<String, String>::value)
                .toList()
        if (unknownComponents.isNotEmpty()) {
            components["attention"] = unknownComponents.joinToString()
        }
        val postCode = components["postcode"]
        if (postCode != null) {
            val p1 = regexPatternCache["^(\\d{5}),\\d{5}"]
            val m1 = p1.matchAt(postCode, 0)
            val p2 = regexPatternCache["\\d+;\\d+"]
            if (postCode.length > 20) {
                components.remove("postcode")
            } else if (p2.matches(postCode)) {
                components.remove("postcode")
            } else if (m1 != null) {
                components["postcode"] = m1.groupValues[1]
            }
        }
        val countryCode = components["country_code"]
        if (abbreviate && countryCode != null) {
            country2Languages[countryCode]?.forEach { language ->
                val abbreviation = abbreviations[language] ?: return@forEach
                abbreviation.forEach abbreviationForEach@{ (component, abbrs) ->
                    var value = components[component] ?: return@abbreviationForEach
                    abbrs.forEach { (src, dest) ->
                        val regex = regexPatternCache["\\b$src\\b"]
                        value = regex.replace(value, dest)
                    }
                    components[component] = value
                }
            }
        }
        val p = regexPatternCache["^https?://"]
        return components.filterTo(hashMapOf()) { (_, value) -> !p.containsMatchIn(value) }
    }

    private fun applyAliases(components: Map<String, String>): MutableMap<String, String> {
        val aliasedComponents: MutableMap<String, String> = hashMapOf()
        val countyCode = components["county_code"]
        val aliases = if (countyCode !in smallDistrictCounties) {
            aliases + ("district" to "state_district")
        } else {
            aliases
        }
        components.forEach { (key, value) ->
            val newKey = aliases[key]?.takeIf { components[it] == null }
            aliasedComponents[key] = value
            if (newKey != null) {
                aliasedComponents[newKey] = value
            }
        }
        return aliasedComponents
    }

    private fun findTemplate(components: Map<String, String>): CountryFormat {
        val countryCode = components["country_code"]
        return if (countryCode != null && Worldwide.countries.containsKey(countryCode)) {
            Worldwide.countries.getValue(countryCode).value
        } else {
            Worldwide.default
        }
    }

    private fun chooseTemplateText(
        template: CountryFormat,
        components: Map<String, String>
    ): String {
        var selected: String =
            template.addressTemplate ?: checkNotNull(Worldwide.default.addressTemplate)
        val required = listOf("road", "postcode")
        val missesAllRequired = required.none(components::containsKey)
        if (missesAllRequired) {
            selected =
                template.fallbackTemplate ?: checkNotNull(Worldwide.default.fallbackTemplate)
        }
        return selected
    }

    private fun getStateCode(state: String, countryCode: String): String? {
        stateCodes[countryCode]?.forEach {
            if (it.value.default.equals(state, ignoreCase = true)) return it.key
        }
        return null
    }

    private fun getCountyCode(county: String, countryCode: String): String? {
        countyCodes[countryCode]?.forEach {
            if (it.value.default.equals(county, ignoreCase = true)) return it.key
        }
        return null
    }

    private fun renderTemplate(template: CountryFormat, components: Map<String, String>): String {
        val callback: MutableMap<String, Any> = HashMap()
        callback["first"] = Function { s: String ->
            val split = s.splitToSequence(regexPatternCache["\\s*\\|\\|\\s*"])
            split.firstOrNull(String::isNotEmpty) ?: ""
        }
        val templateText = chooseTemplateText(template, components)
        val mf: MustacheFactory = DefaultMustacheFactory()
        val m = mf.compile(StringReader(templateText), "example")
        val st = m.execute(StringWriter(), listOf<Any>(components, callback))
        var rendered = cleanupRender(st.toString())
        val postformat = template.postformatReplace
        if (postformat.isNotEmpty()) {
            rendered = postformat.fold(rendered) { acc, jsonNode ->
                val regex = regexPatternCache[jsonNode.search]
                regex.replace(acc, jsonNode.replacement)
            }
        }
        rendered = cleanupRender(rendered)
        return rendered.trim { it <= ' ' } + '\n'
    }

    private fun cleanupRender(rendered: String): String {
        return replacements.entries.fold(rendered) { deduped, (key, value) ->
            val regex = regexPatternCache[key]
            val predupe = regex.replace(deduped, value)
            dedupe(predupe)
        }
    }

    private fun dedupe(rendered: String): String {
        return rendered.lineSequence()
            .map { line ->
                val list = line.trim { it <= ' ' }
                    .split(", ")
                    .map { obj -> obj.trim { it <= ' ' } }
                if (list.none { it.equals("new york", ignoreCase = true) }) {
                    list.distinct()
                } else {
                    list
                }.joinToString()
            }
            .distinct()
            .joinToString(separator = "\n")
    }

    companion object {
        private val regexPatternCache = RegexPatternCache()
        private val uppercaseRegex = "[A-Z]".toRegex()
        private val smallDistrictCounties =
            listOf("BR", "CR", "ES", "NI", "PY", "RO", "TG", "TM", "XK")
        private val replacements: Map<String, String> = mapOf(
            "[\\},\\s]+$" to "",
            "^[,\\s]+" to "",
            "^- " to "",
            ",\\s*," to ", ",
            "[ \t]+,[ \t]+" to ", ",
            "[ \t][ \t]+" to " ",
            "[ \t]\n" to "\n",
            "\n," to "\n",
            ",+" to ",",
            ",\n" to "\n",
            "\n[ \t]+" to "\n",
            "\n+" to "\n",
        )
    }
}
