plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    compileSdk = 33

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
                version = "0.1.4"
                from(components["release"])
            }
        }
    }
}
