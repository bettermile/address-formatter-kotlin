package net.placemarkt

import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.MustacheFactory
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter
import java.util.function.Function
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

class AddressFormatter @JvmOverloads constructor(
    private val abbreviate: Boolean,
    private val appendCountry: Boolean,
    private val replacementFormats: Map<String, String> = emptyMap(),
) {

    init {
        replacementFormats.keys.forEach {
            require(!invalidCountryCode(it)) { "$it is no valid country code" }
        }
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun format(json: String, fallbackCountryCode: String? = null): String {
        val components: Map<String, String> = try {
            val jsonObject = JSONObject(json)
            jsonObject.keys().asSequence().associateWith { jsonObject[it].toString() }
        } catch (e: JSONException) {
            throw IOException("Json processing exception", e)
        }
        return format(components, fallbackCountryCode)
    }

    @JvmOverloads
    fun format(components: Map<String, String>, fallbackCountryCode: String? = null): String {
        var mutableComponents: MutableMap<String, String> = components.toMutableMap()
        mutableComponents = mutableComponents.normalizeFields()
        mutableComponents = determineCountryCode(mutableComponents, fallbackCountryCode)
        val countryCode = requireNotNull(mutableComponents["country_code"])
        if (
            appendCountry &&
            Templates.COUNTRY_NAMES.dataObject.has(countryCode) &&
            "country" !in mutableComponents
        ) {
            mutableComponents["country"] = Templates.COUNTRY_NAMES.dataObject.getString(countryCode)
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
        val country = Workldwide.countries[countryCode]?.value
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
            val oldCountry = Workldwide.countries[oldCountryCode]?.value
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
                !Workldwide.countries.containsKey(countryCode.uppercase())
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
            components.asSequence().filter { (key, _) -> key !in knownComponents }
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
        if (abbreviate && countryCode != null && Templates.COUNTRY_2_LANG.dataObject.has(countryCode)) {
            val languages =
                Templates.COUNTRY_2_LANG.dataObject.getJSONArray(countryCode).toStringList()
            languages.asSequence()
                .flatMap { language ->
                    Templates.ABBREVIATIONS.dataObject.optJSONArray(language)?.toJsonObjectList()
                        .orEmpty()
                }
                .filter { abbreviation -> abbreviation.has("component") }
                .forEach { abbreviation ->
                    abbreviation.getJSONArray("replacements")
                        .toJsonObjectList()
                        .forEach replacementForEach@{ replacement ->
                            val key: String = abbreviation.getString("component")
                            val value = components[key] ?: return@replacementForEach
                            val src = replacement.getString("src")
                            val regex = regexPatternCache["\\b$src\\b"]
                            components[key] = regex.replace(value, replacement.getString("dest"))
                        }
                }
        }
        val p = regexPatternCache["^https?://"]
        return components.filterTo(hashMapOf()) { (_, value) -> !p.containsMatchIn(value) }
    }

    private fun applyAliases(components: Map<String, String>): MutableMap<String, String> {
        val aliasedComponents: MutableMap<String, String> = hashMapOf()
        val originalAliases = Templates.ALIASES.dataArray.toJsonObjectList()
        val countyCode = components["county_code"]
        val aliases = if (countyCode !in smallDistrictCounties) {
            originalAliases.filterNot { it.getString("alias") == "district" } +
                    JSONObject(mapOf("alias" to "district", "name" to "state_district"))
        } else {
            originalAliases
        }
        components.forEach { (key, value) ->
            val newKey = aliases.firstOrNull { alias ->
                alias.getString("alias") == key && components[alias.getString("name")] == null
            }?.getString("name")
            aliasedComponents[key] = value
            if (newKey != null) {
                aliasedComponents[newKey] = value
            }
        }
        return aliasedComponents
    }

    private fun findTemplate(components: Map<String, String>): CountryFormat {
        val countryCode = components["country_code"]
        return if (countryCode != null && Workldwide.countries.containsKey(countryCode)) {
            val replacementFormat = replacementFormats[countryCode]
            Workldwide.countries.getValue(countryCode).value.let { template ->
                when (replacementFormat) {
                    null -> template
                    else -> template.copy(addressTemplate = replacementFormat)
                }
            }
        } else {
            Workldwide.default
        }
    }

    private fun chooseTemplateText(
        template: CountryFormat,
        components: Map<String, String>
    ): String {
        var selected: String =
            template.addressTemplate ?: checkNotNull(Workldwide.default.addressTemplate)
        val required = listOf("road", "postcode")
        val missesAllRequired = required.none(components::containsKey)
        if (missesAllRequired) {
            selected =
                template.fallbackTemplate ?: checkNotNull(Workldwide.default.fallbackTemplate)
        }
        return selected
    }

    private fun getStateCode(state: String, countryCode: String): String? {
        if (!Templates.STATE_CODES.dataObject.has(countryCode)) {
            return null
        }
        val countryCodes = Templates.STATE_CODES.dataObject.getJSONObject(countryCode)
        return countryCodes.keys().asSequence().firstOrNull { key: String ->
            val code = countryCodes[key]
            if (code is JSONObject) {
                code.optString("default").equals(state, ignoreCase = true)
            } else {
                code.toString().equals(state, ignoreCase = true)
            }
        }
    }

    private fun getCountyCode(county: String?, countryCode: String?): String? {
        val country = Templates.COUNTY_CODES.dataObject.optJSONObject(countryCode) ?: return null
        val countyCode = country.keys().asSequence().firstOrNull { countryKey: String ->
            val posCounty = country[countryKey]
            if (posCounty is JSONObject) {
                posCounty.getString("default").equals(county, ignoreCase = true)
            } else {
                posCounty.toString().equals(county, ignoreCase = true)
            }
        }
        return countyCode
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
        private val knownComponents =
            Templates.ALIASES.dataArray.toJsonObjectList().map { it.getString("alias") }
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

        private fun JSONArray.toStringList(): List<String> = List(length(), ::getString)
        private fun JSONArray.toJsonObjectList(): List<JSONObject> = List(length(), ::getJSONObject)
    }
}
