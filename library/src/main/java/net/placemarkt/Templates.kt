package net.placemarkt

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

internal enum class Templates(fileName: String) {
    WORLDWIDE("worldwide.json"),
    COUNTRY_NAMES("countryNames.json"),
    ALIASES("aliases.json"),
    ABBREVIATIONS("abbreviations.json"),
    COUNTRY_2_LANG("country2Lang.json"),
    COUNTY_CODES("countyCodes.json"),
    STATE_CODES("stateCodes.json");

    private interface Constants {
        companion object {
            val jsonWriter = ObjectMapper()
        }
    }

    val data: JsonNode = setData(fileName)

    private fun setData(fileName: String): JsonNode {
        val classLoader = requireNotNull(Thread.currentThread().contextClassLoader)
        val inputStream = classLoader.getResourceAsStream(fileName)
        return Constants.jsonWriter.readTree(inputStream)
    }
}
