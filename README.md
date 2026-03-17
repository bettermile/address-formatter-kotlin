# Address Formatter

[![Build](https://github.com/bettermile/address-formatter-kotlin/actions/workflows/build.yml/badge.svg)](https://github.com/bettermile/address-formatter-kotlin/actions/workflows/build.yml)
[![Publish](https://github.com/bettermile/address-formatter-kotlin/actions/workflows/publish.yml/badge.svg)](https://github.com/bettermile/address-formatter-kotlin/actions/workflows/publish.yml)
![Coverage](https://img.shields.io/endpoint?url=https://gist.githubusercontent.com/gls-octocat/1abe2fe5c88ae55ca5d69e8bda42a3d4/raw/address-formatter-kotlin-coverage-badge.json)

### Overview

A Kotlin library for formatting address components into human-readable address strings. It uses the address templates
from [OpenCage](https://github.com/OpenCageData/address-formatting/) to produce correctly formatted addresses for
countries worldwide.

Key features:
- Formats address component maps (road, city, postcode, country, etc.) into locale-aware address strings
- Supports abbreviation of common terms (e.g. "Avenue" → "Ave")
- Optionally appends the country name to the formatted address
- Allows overriding address templates for specific countries via a [KSP](https://github.com/google/ksp) annotation processor
- Ships as a multiplatform library (JVM and iOS)

This library has only the [Kotlin standard library](https://kotlinlang.org/) as a runtime dependency.

### Requirements

- **Java** 11 or above
- **Gradle** 9.4+ (a wrapper is included; no separate installation needed)
- **Kotlin** 2.3+ (provided transitively via the library)

### Installation

Add the library to your dependencies.

Gradle (Kotlin)

```kotlin
dependencies {
    implementation("com.bettermile:address-formatter-kotlin:0.4.7")
}
```

Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.bettermile:address-formatter-kotlin:0.4.7'
}
```

Maven

```xml
<dependency>
  <groupId>com.bettermile</groupId>
  <artifactId>address-formatter-kotlin</artifactId>
  <version>0.4.7</version>
</dependency> 
```

### Usage

```kotlin
val formatter = AddressFormatter(abbreviate = false, appendCountry = false)
val components = mapOf(
    "country_code" to "US",
    "house_number" to "301",
    "road" to "Hamilton Avenue",
    "neighbourhood" to "Crescent Park",
    "city" to "Palo Alto",
    "postcode" to "94303",
    "county" to "Santa Clara County",
    "state" to "California",
    "country" to "United States",
)
println(formatter.format(components))
/*
301 Hamilton Avenue
Palo Alto, CA 94303
United States of America
*/

val abbreviateFormatter = AddressFormatter(abbreviate = true, appendCountry = false)

println(abbreviateFormatter.format(json))
/*
301 Hamilton Ave
Palo Alto, CA 94303
United States of America
*/

val appendCountryFormatter = AddressFormatter(abbreviate = false, appendCountry = true)
println(appendCountryFormatter.format(json))
/*
301 Hamilton Avenue
Palo Alto, CA 94303
United States of America
*/
```

#### Overwrite formats

If you like to overwrite some default address templates, but still want to keep the component cleanup done by the
formatter, you can overwrite the address template for specific countries in the `AddressFormatter` constructor.

##### Install

Gradle (Kotlin) additions

```kotlin
plugins {
    id("com.google.devtools.ksp") version "<CURRENT_KSP_VERSION>"
}

dependencies {
    ksp("com.bettermile:address-template-processor:0.4.7")
}
```

Gradle (Groovy) additions

```groovy
plugins {
    id 'com.google.devtools.ksp' version '<CURRENT_KSP_VERSION>'
}

dependencies {
    ksp 'com.bettermile:address-template-processor:0.4.7'
}
```

##### Usage

```kotlin
@AddressTemplateDefinition("""
{{{road}}} {{{house_number}}}
{{{postcode}}} {{{city}}}
""",
propertyName = "customUSFormat")
val formatter = AddressFormatter(abbreviate = false, appendCountry = false, mapOf("US" to AddressTemplates.customUSFormat))
val components = mapOf(
    "country_code" to "US",
    "house_number" to "301",
    "road" to "Hamilton Avenue",
    "neighbourhood" to "Crescent Park",
    "city" to "Palo Alto",
    "postcode" to "94303",
    "county" to "Santa Clara County",
    "state" to "California",
    "country" to "United States",
)
println(formatter.format(components))
/*
Hamilton Avenue 301
94303 Palo Alto
*/
```

The supported format is a small subset of the [Mustache specification](https://mustache.github.io/). You can find more
information in the `@AddressTemplateDefinition` documentation.

### How to Run Locally

**Prerequisites:** Java 11+

Clone the repository including its submodule (the OpenCage address-formatting templates):

```bash
git clone --recurse-submodules https://github.com/bettermile/address-formatter-kotlin.git
cd address-formatter-kotlin
```

Build and run all tests:

```bash
./gradlew build
```

Publish to your local Maven repository (useful for testing the library in another project):

```bash
./gradlew publishToMavenLocal
```

To regenerate the address templates from the OpenCage submodule (e.g. after updating the submodule):

```bash
./gradlew :YamlConverter:run
```

### How to Deploy

Releases are published to [Maven Central](https://central.sonatype.com/). The full release process is documented in
[docs/release.md](docs/release.md). In summary:

1. Bump the version number in `README.md` (Installation and Overwrite formats sections) and in the publishing
   configuration of `formatter/build.gradle.kts`, `template/build.gradle.kts`, and
   `template-processor/build.gradle.kts`.
2. Commit the changes, create a Git tag with the new version name, and push both.
3. Create a GitHub release from the new tag.
4. The [Publishing workflow](.github/workflows/publish.yml) is triggered automatically by the tag push and publishes the
   artifacts to Maven Central.

### License

This project is licensed under the Apache 2.0. See the [LICENSE](LICENSE.txt) for details.

### Acknowledgements

- [OpenCage](https://github.com/OpenCageData/address-formatting/)
- [Java implementation](https://github.com/placemarkt/address-formatter-java)
- [Javascript implementation](https://github.com/fragaria/address-formatter)
