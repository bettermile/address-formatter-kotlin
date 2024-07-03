/*
 * Copyright 2022 GLS eComLab
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
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    compileSdk = libs.versions.android.sdk.get().toInt()

    defaultConfig {
        minSdk = 24 // could maybe be lower
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        allWarningsAsErrors = true
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    namespace = "net.placemarkt"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(libs.mustache)

    testImplementation(libs.jackson.core)
    testImplementation(libs.jackson.yaml)
    testImplementation(libs.orgjson)
    testImplementation(libs.kotlin.test.junit)
}

afterEvaluate {
    publishing {
        repositories.maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/bettermile/address-formatter-android")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        publications {
            create<MavenPublication>("addressFormatter") {
                groupId = "com.bettermile.betterroute"
                artifactId = "address-formatter-android"
                version = "0.1.8"
                from(components["release"])
            }
        }
    }
}
