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
    kotlin("native.cocoapods")
    alias(libs.plugins.dokka)
    alias(libs.plugins.maven.publish)
}

repositories {
    mavenCentral()
}

val libraryName = "Address Formatter Kotlin"
val libraryLicense = "The Apache Software License, Version 2.0"
val libraryVersion = "0.3.3"
val libraryDescription = "An address components formatter for Kotlin"
val libraryUrl = "https://github.com/bettermile/address-formatter-kotlin"
val libraryAuthorName = "Bettermile"
val libraryAuthorEmail = "app+maven@bettermile.com"
val librarySourceControlUrl = "scm:git:git://github.com/bettermile/address-formatter-kotlin.git"

kotlin {
    jvm()
    jvmToolchain(11)

    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        jvmMain.dependencies {
            implementation(libs.mustache)
        }
    }
    cocoapods {
        name = libraryName
        version = libraryVersion
        summary = libraryDescription
        homepage = libraryUrl
        license = libraryLicense
        authors = "$libraryAuthorName <$libraryAuthorEmail>"
        source = librarySourceControlUrl
        pod("GRMustache") {
            version = "7.3.2"
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

    coordinates("com.bettermile", "address-formatter-kotlin", libraryVersion)

    pom {
        name.set(libraryName)
        packaging = "jar"
        description.set(libraryDescription)
        url.set(libraryUrl)
        scm {
            url.set(libraryUrl)
            connection.set(librarySourceControlUrl)
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
                email.set(libraryAuthorEmail)
                organization.set(libraryAuthorName)
                organizationUrl.set("https://bettermile.com/")
            }
        }
    }
}
