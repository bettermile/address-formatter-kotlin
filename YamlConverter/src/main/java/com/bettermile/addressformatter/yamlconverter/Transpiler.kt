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

package com.bettermile.addressformatter.yamlconverter

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.NullNode
import com.fasterxml.jackson.databind.node.ObjectNode
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.Locale
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.deleteRecursively
import kotlin.io.path.nameWithoutExtension
import kotlin.io.path.pathString
import kotlin.streams.asSequence

object Transpiler {

    @JvmStatic
    fun main(args: Array<String>) {
        // delete old generated files, to clean up old generated files, which might not be needed anymore
        Paths.get(KOTLIN_DESTINATION_DIR).deleteAllFiles()
        Paths.get(TEST_DESTINATION_DIR).deleteAllFiles()

        val yamlFactory = YAMLFactory()
        val yamlReader = ObjectMapper(yamlFactory)
        transpileWorldwide(yamlReader)
        transpileCountryNames(yamlReader)
        transpileAliases(yamlFactory, yamlReader)
        transpileAbbreviations(yamlReader)
        transpileCountry2Lang(yamlReader)
        transpileCountyCodes(yamlReader)
        transpileStateCodes(yamlReader)
        testCases(yamlReader, yamlFactory)
    }

    private const val KOTLIN_DESTINATION_DIR = "formatter/src/commonMain/kotlin/"
    private const val TEST_DESTINATION_DIR = "formatter/src/commonTest/kotlin/"

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

    private fun transpileAliases(yamlFactory: YAMLFactory, yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/components.yaml")
            val array = yamlReader.readValues(yamlFactory.createParser(path.toFile()), ObjectNode::class.java).readAll()
            ComponentsTranspiler.yamlToFile(array).writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileAbbreviations(yamlReader: ObjectMapper) {
        try {
            val abbreviations = Files.list(Paths.get("address-formatting/conf/abbreviations")).use { paths ->
                paths.sorted(PATH_BY_NAME_COMPARATOR).asSequence().associate { path: Path ->
                    val language = path.fileName.nameWithoutExtension.uppercase(Locale.getDefault())
                    val yaml = readFile(path.toString())
                    val obj = yamlReader.readTree(yaml)
                    language to (obj as ObjectNode)
                }
            }
            AbbreviationsTranspiler.yamlToFile(abbreviations).writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun testCases(yamlReader: ObjectMapper, yamlFactory: YAMLFactory) {
        try {
            val paths = Files.list(Paths.get("address-formatting/testcases")).flatMap { path ->
                try {
                    Files.list(path)
                } catch (e: IOException) {
                    throw RuntimeException("error while reading path $path", e)
                }
            }
            paths.sorted(PATH_BY_NAME_COMPARATOR).forEach { path: Path ->
                val array: List<ObjectNode> =
                    yamlReader.readValues(yamlFactory.createParser(path.toFile()), JsonNode::class.java).readAll()
                        .filterNot { it is NullNode }
                        .filterIsInstance<ObjectNode>()
                TestCasesTranspiler.yamlToFile(path, array).writeTo(Paths.get(TEST_DESTINATION_DIR))
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileCountry2Lang(yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/country2lang.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readTree(yaml) as ObjectNode
            Country2LangTranspiler.yamlToFile(obj).writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileCountyCodes(yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/county_codes.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readTree(yaml) as ObjectNode
            RegionNamingTranspiler.yamlToFile("CountyCodes", "countyCodes", obj)
                .writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun transpileStateCodes(yamlReader: ObjectMapper) {
        try {
            val path = Paths.get("address-formatting/conf/state_codes.yaml")
            val yaml = readFile(path.toString())
            val obj = yamlReader.readTree(yaml) as ObjectNode
            RegionNamingTranspiler.yamlToFile("StateCodes", "stateCodes", obj)
                .writeTo(Paths.get(KOTLIN_DESTINATION_DIR))
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    fun readFile(path: String): String {
        val encoded = Files.readAllBytes(Paths.get(path))
        return String(encoded, StandardCharsets.UTF_8)
    }

    @OptIn(ExperimentalPathApi::class)
    private fun Path.deleteAllFiles(packageName: String = DEFAULT_PACKAGE) {
        val folderName = packageName.replace('.', '/')
        resolve(folderName).deleteRecursively()
    }

    private val PATH_BY_NAME_COMPARATOR = compareBy(Path::pathString)
}
