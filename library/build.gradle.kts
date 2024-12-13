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

import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.publish)
}

repositories {
    mavenCentral()
}

kotlin {
    jvm()
    jvmToolchain(11)

    sourceSets {
        jvmMain.dependencies {
            implementation(libs.mustache)
        }
    }
}

dependencies {
    commonTestImplementation(libs.kotlin.test)
}

mavenPublishing {
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL, automaticRelease = false)
    val signReleaseEnabled = project.properties["signReleaseEnabled"]
    if (signReleaseEnabled == "true") {
        signAllPublications()
    }

    coordinates("com.bettermile", "address-formatter-kotlin", "0.3.3")

    pom {
        name.set("Address Formatter Kotlin")
        packaging = "jar"
        description.set("An address components formatter for Kotlin")
        url.set("https://github.com/bettermile/address-formatter-kotlin")
        scm {
            url.set("https://github.com/bettermile/address-formatter-kotlin")
            connection.set("scm:git:git://github.com/bettermile/address-formatter-kotlin.git")
            developerConnection.set("scm:git:ssh://github.com/bettermile/address-formatter-kotlin.git")
            tag.set(System.getenv("VCS_TAG"))
        }
        licenses {
            license {
                name.set("The Apache Software License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                email.set("app+maven@bettermile.com")
                organization.set("Bettermile")
                organizationUrl.set("https://bettermile.com/")
            }
        }
    }
}
