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
    implementation(libs.jackson.core)
    implementation(libs.jackson.yaml)
    implementation(libs.mustache)

    testImplementation(libs.kotlin.test.junit)
}

val sourcesJar by tasks.register<Jar>("sourcesJar") {
    from(android.sourceSets["main"].java.srcDirs)
    archiveClassifier.set("sources")
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
                artifact(sourcesJar)
                from(components["release"])
            }
        }
    }
}
