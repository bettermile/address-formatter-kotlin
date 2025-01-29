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

import org.gradle.jvm.tasks.Jar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.ksp)
    bettermile.`maven-publish`
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    jvmToolchain(11)

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain {
            kotlin {
                srcDirs(project.layout.buildDirectory.dir("generated/ksp/metadata/commonMain/kotlin"))
            }
        }
    }
}

dependencies {
    kspCommonMainMetadata(project(":template-processor"))

    commonMainImplementation(project(":template"))

    commonTestImplementation(libs.kotlin.test)
}

tasks.withType<KotlinCompilationTask<*>> {
    if(name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

tasks.withType<Jar> {
    if(name.contains("sourcesJar", ignoreCase = true)) {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}

mavenPublishing {
    coordinates("com.bettermile", "address-formatter-kotlin", "0.3.4")

    pom {
        name.set("Address Formatter Kotlin")
        description.set("An address components formatter for Kotlin")
    }
}
