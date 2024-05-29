buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.build.tools.gradle)
        classpath(libs.kotlin.gradle)
    }
}

plugins {
    alias(libs.plugins.compose.compiler) apply false
}
