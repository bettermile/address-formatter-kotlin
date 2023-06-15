package net.placemarkt

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ArrayNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.github.mustachejava.DefaultMustacheFactory
import com.github.mustachejava.MustacheFactory
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
    private val yamlReader = ObjectMapper(YAMLFactory())

    init {
        replacementFormats.keys.forEach {
            require(!invalidCountryCode(it)) { "$it is no valid country code" }
        }
    }

    @JvmOverloads
    @Throws(IOException::class)
    fun format(json: String, fallbackCountryCode: String? = null): String {
        val factory = TypeFactory.defaultInstance()
        val type =
            factory.constructMapType(HashMap::class.java, String::class.java, String::class.java)
        val components: Map<String, String> = try {
            yamlReader.readValue(json, type)
        } catch (e: JsonProcessingException) {
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
        if (appendCountry && Templates.COUNTRY_NAMES.data.has(countryCode) && mutableComponents["country"] == null) {
            mutableComponents["country"] = Templates.COUNTRY_NAMES.data[countryCode].asText()
        }
        mutableComponents = applyAliases(mutableComponents)
        val template = findTemplate(mutableComponents)
        mutableComponents = cleanupInput(mutableComponents, template["replace"])
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
            require(!invalidCountryCode(fallbackCountryCode)) {
                "No country code provided. Use fallbackCountryCode?"
            }
            countryCode = fallbackCountryCode
        }

        countryCode = countryCode.uppercase()
        if (countryCode == "UK") {
            countryCode = "GB"
        }
        val country = Templates.WORLDWIDE.data[countryCode]
        if (country != null && country.has("use_country")) {
            val oldCountryCode: String = countryCode
            countryCode = country["use_country"].asText().uppercase()
            if (country.has("change_country")) {
                var newCountry = country["change_country"].asText()
                val regex = regexPatternCache["\\$(\\w*)"]
                newCountry = regex.replace(newCountry) { matchResult ->
                    val match = matchResult.groupValues[1]
                    components[match] ?: ""
                }
                components["country"] = newCountry
            }
            val oldCountry = Templates.WORLDWIDE.data[oldCountryCode]
            val oldCountryAddComponent = oldCountry["add_component"]
            if (oldCountryAddComponent != null) {
                val completeText = oldCountryAddComponent.textValue()
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
                !Templates.WORLDWIDE.data.has(countryCode.uppercase())
    }

    private fun cleanupInput(
        components: MutableMap<String, String>,
        replacements: JsonNode?
    ): MutableMap<String, String> {
        val country = components["country"]
        val state = components["state"]
        if (country != null && state != null && country.toIntOrNull() != null) {
            components["country"] = state
            components.remove("state")
        }
        if (replacements != null && replacements.size() > 0) {
            for (componentEntry in components.entries) {
                val component = componentEntry.key
                var componentValue = componentEntry.value
                replacements.forEach { replacement ->
                    val replacementText = replacement[0].asText()
                    if (replacementText.startsWith("$component=")) {
                        val value = replacementText.substring(component.length + 1)
                        if (componentValue == value) {
                            componentValue = replacement[1].asText()
                            componentEntry.setValue(componentValue)
                        }
                    } else {
                        val regex = regexPatternCache[replacementText]
                        componentValue = regex.replace(componentValue, replacement[1].asText())
                        componentEntry.setValue(componentValue)
                    }
                }
            }
        }
        val stateValue = components["state"]
        if (!components.containsKey("state_code") && stateValue != null) {
            val stateCode = getStateCode(stateValue, requireNotNull(components["country_code"]))
            if (stateCode != null) {
                components["state_code"] = stateCode
            }
            val p = regexPatternCache["^washington,? d\\.?c\\.?"]
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
        if (abbreviate && countryCode != null && Templates.COUNTRY_2_LANG.data.has(countryCode)) {
            val languages = Templates.COUNTRY_2_LANG.data[countryCode]
            languages.asSequence()
                .flatMap { language ->
                    Templates.ABBREVIATIONS.data[language.textValue()] ?: emptyList()
                }
                .filter { abbreviation -> abbreviation.has("component") }
                .forEach { abbreviation ->
                    abbreviation["replacements"].forEach replacementForEach@{ replacement ->
                        val key: String = abbreviation["component"].asText()
                        val value = components[key] ?: return@replacementForEach
                        val src = replacement["src"].asText()
                        val regex = regexPatternCache["\\b$src\\b"]
                        components[key] = regex.replace(value, replacement["dest"].asText())
                    }
                }
        }
        val p = regexPatternCache["^https?://"]
        return components.filterTo(hashMapOf()) { (_, value) -> !p.matches(value) }
    }

    private fun applyAliases(components: Map<String, String>): MutableMap<String, String> {
        val aliasedComponents: MutableMap<String, String> = hashMapOf()
        val aliases = Templates.ALIASES.data
        components.forEach { (key, value) ->
            val newKey = aliases.firstOrNull { alias ->
                alias["alias"].asText() == key && components[alias["name"].asText()] == null
            }?.get("name")?.asText()
            aliasedComponents[key] = value
            if (newKey != null) {
                aliasedComponents[newKey] = value
            }
        }
        return aliasedComponents
    }

    private fun findTemplate(components: Map<String, String>): JsonNode {
        val countryCode = components["country_code"]
        return if (countryCode != null && Templates.WORLDWIDE.data.has(countryCode)) {
            val replacementFormat = replacementFormats[countryCode]
            Templates.WORLDWIDE.data[countryCode].let { template ->
                when (replacementFormat) {
                    null -> template
                    else -> template.deepCopy<ObjectNode>()
                        .put("address_template", replacementFormat)
                }
            }
        } else {
            Templates.WORLDWIDE.data["default"]
        }
    }

    private fun chooseTemplateText(template: JsonNode, components: Map<String, String>): JsonNode {
        var selected: JsonNode = if (template.has("address_template")) {
            Templates.WORLDWIDE.data[template["address_template"].asText()]
                ?: template["address_template"]
        } else {
            val defaults = Templates.WORLDWIDE.data["default"]
            Templates.WORLDWIDE.data[defaults["address_template"].textValue()]
        }
        val required = listOf("road", "postcode")
        val missesAllRequired = required.none(components::containsKey)
        if (missesAllRequired) {
            selected = if (template.has("fallback_template")) {
                Templates.WORLDWIDE.data[template["fallback_template"].asText()]
                    ?: template["fallback_template"]
            } else {
                val defaults = Templates.WORLDWIDE.data["default"]
                Templates.WORLDWIDE.data[defaults["fallback_template"].textValue()]
            }
        }
        return selected
    }

    private fun getStateCode(state: String, countryCode: String): String? {
        if (!Templates.STATE_CODES.data.has(countryCode)) {
            return null
        }
        val countryCodes = Templates.STATE_CODES.data[countryCode]
        return countryCodes.fieldNames().asSequence().firstOrNull { key: String? ->
            val code = countryCodes[key]
            if (code.isObject) {
                code["default"]?.asText()?.equals(state, ignoreCase = true) ?: false
            } else {
                code.asText().equals(state, ignoreCase = true)
            }
        }
    }

    private fun getCountyCode(county: String?, countryCode: String?): String? {
        val country = Templates.COUNTY_CODES.data[countryCode] ?: return null
        val countyCode = country.firstOrNull { posCounty: JsonNode ->
            if (posCounty.isObject) {
                posCounty["default"]?.asText()?.equals(county, ignoreCase = true) ?: false
            } else {
                posCounty.asText().equals(county, ignoreCase = true)
            }
        }
        return countyCode?.asText()
    }

    private fun renderTemplate(template: JsonNode, components: Map<String, String>): String {
        val callback: MutableMap<String, Any> = HashMap()
        callback["first"] = Function { s: String ->
            val split = s.splitToSequence(regexPatternCache["\\s*\\|\\|\\s*"])
            split.firstOrNull(String::isNotEmpty) ?: ""
        }
        val templateText = chooseTemplateText(template, components)
        val mf: MustacheFactory = DefaultMustacheFactory()
        val m = mf.compile(StringReader(templateText.asText()), "example")
        val st = m.execute(StringWriter(), listOf<Any>(components, callback))
        var rendered = cleanupRender(st.toString())
        val postformat = template["postformat_replace"] as? ArrayNode
        if (postformat != null) {
            rendered = postformat.fold(rendered) { acc, jsonNode ->
                val regex = regexPatternCache[jsonNode[0].asText()]
                regex.replace(acc, jsonNode[1].asText())
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
                line.trim { it <= ' ' }
                    .splitToSequence(", ")
                    .map { obj -> obj.trim { it <= ' ' } }
                    .distinct()
                    .joinToString()
            }
            .distinct()
            .joinToString(separator = "\n")
    }

    companion object {
        private val regexPatternCache = RegexPatternCache()
        private val knownComponents = Templates.ALIASES.data.map { it["alias"].textValue() }
        private val uppercaseRegex = "[A-Z]".toRegex()
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
