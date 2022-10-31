plugins {
    id("com.android.library")
    kotlin("android")
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
