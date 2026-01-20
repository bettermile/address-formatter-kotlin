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
    kotlin("multiplatform")
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
}

mavenPublishing {
    coordinates("com.bettermile", "address-template-kotlin", "0.4.7")

    pom {
        name.set("Address Template")
        description.set("Address templates used to format addresses in Kotlin")
    }
}
