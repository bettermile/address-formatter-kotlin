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
    application
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("com.bettermile.addressformatter.yamlconverter.Transpiler")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":template"))
    implementation(libs.jackson.core)
    implementation(libs.jackson.yaml)
    implementation(libs.kotlinpoet)
}

tasks.run.configure {
    workingDir = rootDir
}
