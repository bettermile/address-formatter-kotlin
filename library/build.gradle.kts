plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    compileSdk = 33

    defaultConfig {
        minSdk = 24 // could maybe be lower
        targetSdk = 33
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        allWarningsAsErrors = true
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    namespace = "net.placemarkt"
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.7")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.7")
    implementation("com.google.guava:guava:30.0-jre")
    implementation("com.github.spullara.mustache.java:compiler:0.9.7")

    testImplementation("junit:junit:4.13.2")
}

val sourcesJar by tasks.register<Jar>("sourcesJar") {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
}

afterEvaluate {
    publishing {
        repositories.maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/gls-ecl/address-formatter-android")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
        publications {
            create<MavenPublication>("addressFormatter") {
                groupId = "com.bettermile.betterroute"
                artifactId = "address-formatter-android"
                version = "0.1.0-SNAPSHOT"
                artifact(sourcesJar)
                from(components["release"])
            }
        }
    }
}
