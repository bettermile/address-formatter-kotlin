plugins {
    java
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClass.set("net.placemarkt.yamlconverter.Transpiler")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-core:2.12.7")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.12.7")
    implementation("com.google.guava:guava:30.0-jre")
    implementation("com.github.spullara.mustache.java:compiler:0.9.7")

    testImplementation("junit:junit:4.13.2")
}
