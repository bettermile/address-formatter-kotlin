package net.placemarkt.yamlconverter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.IOException
import java.io.PrintWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Locale
import java.util.function.Consumer
import java.util.stream.Stream

object Transpiler {

    @JvmStatic
    fun main(args: Array<String>) {
        val yamlReader = ObjectMapper(YAMLFactory())
        val yamlFactory = YAMLFactory()
        val jsonWriter = ObjectMapper()
        transpileWorldwide(yamlReader)
        transpileCountryNames(yamlReader)
        transpileAliases(yamlFactory, jsonWriter)
        transpileAbbreviations(yamlReader, jsonWriter)
        transpileCountry2Lang(yamlReader, jsonWriter)
        transpileCountyCodes(yamlReader, jsonWriter)
        transpileStateCodes(yamlReader, jsonWriter)
        testCases(yamlReader, yamlFactory, jsonWriter)
    }

    private const val DESTINATION_DIR = "library/src/main/resources/"
    private const val KOTLIN_DESTINATION_DIR = "library/src/main/java/"

    private fun transpileWorldwide(yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/countries/worldwide.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readTree(yaml) as ObjectNode
            WorldwideTranspiler.yamlToFile(obj).writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileCountryNames(yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/country_codes.yaml")
            val yaml = readFile(path.toString())
            val formattedYaml = yaml.replace(" # ", " ")
            val obj = yamlReader.readTree(formattedYaml) as ObjectNode
            CountryNamesTranspiler.yamlToFile(obj).writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileAliases(yamlFactory: YAMLFactory, jsonWriter: ObjectMapper) {
        try {
            val node = jsonWriter.createArrayNode()
            val path = Paths.get("address-formatting/conf/components.yaml")
            val yamlParser = yamlFactory.createParser(path.toFile())
            val nodes = jsonWriter
                .readValues(yamlParser, object : TypeReference<ObjectNode>() {})
                .readAll()
            nodes.forEach { component: ObjectNode ->
                val aliases = component.withArray("aliases")
                aliases.forEach(Consumer { alias: JsonNode ->
                    val componentNode = node.addObject()
                    componentNode.put("alias", alias.textValue())
                    componentNode.put("name", component["name"].textValue())
                })
                val componentNode = node.addObject()
                componentNode.put("alias", component["name"].textValue())
                componentNode.put("name", component["name"].textValue())
            }
            PrintWriter(DESTINATION_DIR + "aliases.json").use { out -> out.println(node.toString()) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileAbbreviations(yamlReader: ObjectMapper, jsonWriter: ObjectMapper) {
        val abbreviations = jsonWriter.createObjectNode()
        try {
            Files.list(Paths.get("address-formatting/conf/abbreviations")).use { paths ->
                paths.sorted(
                    PATH_BY_NAME_COMPARATOR
                ).forEach { path: Path ->
                    try {
                        val fileNameWithExtension = path.fileName.toString()
                        val pos = fileNameWithExtension.lastIndexOf(".")
                        val fileName =
                            fileNameWithExtension.substring(0, pos).uppercase(Locale.getDefault())
                        val yaml = readFile(path.toString())
                        val obj = yamlReader.readValue(yaml, Any::class.java)
                        val country = jsonWriter.valueToTree<JsonNode>(obj)
                        val fieldName = country.fieldNames()
                        val countryComponentArray = jsonWriter.createArrayNode()
                        while (fieldName.hasNext()) {
                            val type = fieldName.next()
                            val replacements = country[type]
                            val srcs = replacements.fieldNames()
                            val pairs = jsonWriter.createArrayNode()
                            while (srcs.hasNext()) {
                                val src = srcs.next()
                                val dest = replacements[src].textValue()
                                val pair = pairs.addObject()
                                pair.put("src", src)
                                pair.put("dest", dest)
                            }
                            val countryComponent = jsonWriter.createObjectNode()
                            countryComponent.put("component", type)
                            countryComponent.set<JsonNode>("replacements", pairs)
                            countryComponentArray.add(countryComponent)
                        }
                        abbreviations.set<JsonNode>(fileName, countryComponentArray)
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
            PrintWriter(DESTINATION_DIR + "abbreviations.json").use { out ->
                out.println(
                    abbreviations.toString()
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun testCases(yamlReader: ObjectMapper, yamlFactory: YAMLFactory, jsonWriter: ObjectMapper) {
        try {
            val rootNode = jsonWriter.createArrayNode()
            val paths = Stream.of(
                "address-formatting/testcases/countries",
                "address-formatting/testcases/other"
            ).flatMap { path: String ->
                try {
                    return@flatMap Files.list(Paths.get(path))
                } catch (e: IOException) {
                    throw RuntimeException("error while reading path $path", e)
                }
            }
            paths.sorted(PATH_BY_NAME_COMPARATOR).forEach { path: Path ->
                try {
                    val yaml = readFile(path.toString())
                    yamlFactory.createParser(yaml).use { parser ->
                        yamlReader.readValues(parser, Any::class.java)
                            .forEachRemaining { element: Any? ->
                                if (element != null) {
                                    val node = jsonWriter.valueToTree<ObjectNode>(element)
                                    val description = node["description"].asText()
                                    val fileName = path.fileName.toString()
                                    node.put(
                                        "description",
                                        fileName.substring(
                                            0,
                                            fileName.length - 5
                                        ) + " - " + description
                                    )
                                    rootNode.add(node)
                                }
                            }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            val out = PrintWriter("library/src/test/resources/countries.json")
            out.println(rootNode.toString())
            out.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileCountry2Lang(yamlReader: ObjectMapper, jsonWriter: ObjectMapper) {
        val node: ObjectNode?
        try {
            val path = Paths.get("address-formatting/conf/country2lang.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readValue(yaml, Any::class.java)
            node = jsonWriter.valueToTree(obj)
            val countries = node.fieldNames()
            while (countries.hasNext()) {
                val country = countries.next()
                val languages = node[country].asText()
                val languagesArray = node.putArray(country)
                val languagesSplit =
                    languages.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                for (s in languagesSplit) {
                    languagesArray.add(s.uppercase(Locale.getDefault()))
                }
            }
            PrintWriter(DESTINATION_DIR + "country2Lang.json").use { out -> out.println(node.toString()) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /*
    * TODO: Look into formatting this data in such a way that makes it easier to query
    */
    private fun transpileCountyCodes(yamlReader: ObjectMapper, jsonWriter: ObjectMapper) {
        val node: ObjectNode?
        try {
            val path = Paths.get("address-formatting/conf/county_codes.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readValue(yaml, Any::class.java)
            node = jsonWriter.valueToTree(obj)
            PrintWriter(DESTINATION_DIR + "countyCodes.json").use { out -> out.println(node.toString()) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /*
    *TODO: Look into formatting this data in such a way that makes it easier to query
    */
    private fun transpileStateCodes(yamlReader: ObjectMapper, jsonWriter: ObjectMapper) {
        val node: ObjectNode?
        try {
            val path = Paths.get("address-formatting/conf/state_codes.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readValue(yaml, Any::class.java)
            node = jsonWriter.valueToTree(obj)
            PrintWriter(DESTINATION_DIR + "stateCodes.json").use { out -> out.println(node.toString()) }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun readFile(path: String): String {
        val encoded = Files.readAllBytes(Paths.get(path))
        return String(encoded, StandardCharsets.UTF_8)
    }

    private val PATH_BY_NAME_COMPARATOR = compareBy { path: Path -> path.fileName.toString() }
}
