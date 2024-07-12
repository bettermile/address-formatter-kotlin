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

import org.jetbrains.dokka.gradle.DokkaTask

plugins {
    kotlin("jvm")
    alias(libs.plugins.dokka)
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.mustache)

    testImplementation(libs.kotlin.test.junit)
}

kotlin {
    jvmToolchain(11)
}

val sourcesJar by tasks.register<Jar>("sourcesJar") {
    group = "publishing"
    from(sourceSets.main.get().java.srcDirs)
    archiveClassifier.set("sources")
}

val dokkaJavadocJar by tasks.register<Jar>("dokkaJavadocJar") {
    group = "publishing"
    dependsOn(tasks.dokkaJavadoc)
    from(tasks.dokkaJavadoc.flatMap(DokkaTask::outputDirectory))
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    publishing {
        repositories.maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/bettermile/address-formatter-kotlin")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        publications {
            create<MavenPublication>("addressFormatter") {
                groupId = "com.bettermile"
                artifactId = "address-formatter-kotlin"
                version = "0.2.0"
                from(components["kotlin"])
                artifact(sourcesJar)
                artifact(dokkaJavadocJar)
            }
        }
    }
}
