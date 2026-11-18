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

import org.jetbrains.kotlin.gradle.dsl.HasConfigurableKotlinCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.jvm.KotlinJvmTarget

// Compile with the JDK running Gradle (no toolchains), but target this Java version.
// See https://jakewharton.com/gradle-toolchains-are-rarely-a-good-idea/
val jvmTargetVersion = 11

tasks.withType<JavaCompile>().configureEach {
    options.release.set(jvmTargetVersion)
}

plugins.withId("org.jetbrains.kotlin.jvm") {
    extensions.configure<KotlinJvmProjectExtension> {
        configureJvmCompatibility()
    }
}

plugins.withId("org.jetbrains.kotlin.multiplatform") {
    extensions.configure<KotlinMultiplatformExtension> {
        targets.withType<KotlinJvmTarget>().configureEach {
            configureJvmCompatibility()
        }
    }
}

private fun HasConfigurableKotlinCompilerOptions<KotlinJvmCompilerOptions>.configureJvmCompatibility() {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(jvmTargetVersion.toString()))
        freeCompilerArgs.add("-Xjdk-release=$jvmTargetVersion")
    }
}
