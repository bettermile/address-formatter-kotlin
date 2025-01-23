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

package com.bettermile.addressformatter.template.processor

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import com.tschuchort.compiletesting.configureKsp
import com.tschuchort.compiletesting.sourcesGeneratedBySymbolProcessor
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.File
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@OptIn(ExperimentalCompilerApi::class)
@RunWith(Parameterized::class)
class AddressTemplateProcessorTest(
    @Suppress("unused") private val testName: String,
    private val directory: File,
) {

    @Test
    fun `should output correct values`() {
        val sourceDirectory = File(directory, "src")
        val sources = sourceDirectory.listFilesRecursively()
            .map { SourceFile.kotlin(it.toRelativeString(sourceDirectory), it.readText()) }
        val successDir = File(directory, "success")
        if (successDir.exists()) {
            val expectedOutput = successDir.listFilesRecursively().map {
                it.toRelativeString(successDir) to it.readText()
            }

            testSuccessfulTemplateCreation(expectedOutput, sources)
        } else {
            val errorMessages = File(File(directory, "error"), "messages.txt").readLines()

            testErrorTemplateCreation(errorMessages, sources)
        }
    }

    private fun testSuccessfulTemplateCreation(
        expectedOutput: List<Pair<String, String>>,
        sourceFiles: List<SourceFile>,
    ) {
        val result = compile(sourceFiles)

        assertEquals(KotlinCompilation.ExitCode.OK, result.exitCode)
        val outputFiles = result.sourcesGeneratedBySymbolProcessor.toList()
        assertEquals(
            expected = expectedOutput.size,
            actual = outputFiles.size,
            message = "expected ${expectedOutput.size} output files but got ${outputFiles.size}",
        )
        expectedOutput.forEach { (name, content) ->
            val outputFile = assertNotNull(outputFiles.find { it.path.endsWith(name) })
            assertEquals(content, outputFile.readText())
        }
    }

    private fun testErrorTemplateCreation(expectedErrorMessages: List<String>, sourceFiles: List<SourceFile>) {
        val result = compile(sourceFiles)

        assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.exitCode)

        expectedErrorMessages.forEach {
            assertContains(result.messages.lines(), it)
        }
    }

    private fun compile(sourceFiles: List<SourceFile>) =
        KotlinCompilation().apply {
            sources = sourceFiles
            multiplatform = true
            inheritClassPath = true
            configureKsp(useKsp2 = true) {
                symbolProcessorProviders += AddressTemplateProcessorProvider()
            }
        }.compile()

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun data(): List<Array<Any>> {
            val testCases = Thread.currentThread().contextClassLoader!!
                .getResource("testcases/")!!
                .file
                .let(::File)
                .listFiles()!!
            return testCases.map {
                arrayOf(it.name, it)
            }
        }

        private fun File.listFilesRecursively(
            todo: List<File> = emptyList(),
            result: List<File> = emptyList(),
        ): List<File> {
            val (directories, files) = listFiles().orEmpty().partition(File::isDirectory)
            val newToDo = todo + directories
            val newResult = result + files
            return if (newToDo.isEmpty()) {
                newResult
            } else {
                newToDo.first().listFilesRecursively(newToDo.drop(1), newResult)
            }
        }
    }
}
