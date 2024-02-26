package net.placemarkt

import org.json.JSONArray
import org.json.JSONObject

internal enum class Templates(fileName: String) {
    COUNTRY_NAMES("countryNames.json"),
    ALIASES("aliases.json"),
    ABBREVIATIONS("abbreviations.json"),
    COUNTRY_2_LANG("country2Lang.json"),
    COUNTY_CODES("countyCodes.json"),
    STATE_CODES("stateCodes.json");

    val dataObject: JSONObject by lazy { setDataObject(fileName) }
    val dataArray: JSONArray by lazy { setDataArray(fileName) }

    private fun setDataObject(fileName: String): JSONObject {
        return JSONObject(jsonString(fileName))
    }

    private fun setDataArray(fileName: String): JSONArray {
        return JSONArray(jsonString(fileName))
    }

    private fun jsonString(fileName: String): String {
        val classLoader = requireNotNull(Thread.currentThread().contextClassLoader)
        val inputStream = classLoader.getResourceAsStream(fileName)
        return inputStream.bufferedReader().useLines(Sequence<String>::single)
    }
}
