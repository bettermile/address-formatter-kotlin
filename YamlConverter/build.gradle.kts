plugins {
    java
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

application {
    mainClass.set("net.placemarkt.yamlconverter.Transpiler")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.jackson.core)
    implementation(libs.jackson.yaml)

    testImplementation(libs.kotlin.test.junit)
}

tasks.run.configure {
    workingDir = rootDir
}
