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

plugins {
    kotlin("jvm")
    bettermile.`maven-publish`
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(project(":template"))
    implementation(libs.kotlinpoet.ksp)
    implementation(libs.ksp.api)

    testImplementation(libs.kotlin.test)
    testImplementation(libs.ksp.test)
}

mavenPublishing {
    coordinates("com.bettermile", "address-template-processor", "0.4.2")

    pom {
        name.set("Address Template Processor")
        description.set("A Kotlin symbol processor to convert address templates to Kotlin code")
    }
}
