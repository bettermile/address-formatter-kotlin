# Address Formatter

A library for formatting addresses.

### Overview

This project uses the address templates from [OpenCage](https://github.com/OpenCageData/address-formatting/) to format
addresses.

This library has only the [kotlin standard library](https://kotlinlang.org/) as dependency.

For Java 11 and above.

### Installation

Add the library to your dependencies.

Gradle (Kotlin)

```kotlin
dependencies {
    implementation("com.bettermile:address-formatter-kotlin:0.4.3")
}
```

Gradle (Groovy)

```groovy
dependencies {
    implementation 'com.bettermile:address-formatter-kotlin:0.4.3'
}
```

Maven

```xml
<dependency>
  <groupId>com.bettermile</groupId>
  <artifactId>address-formatter-kotlin</artifactId>
  <version>0.4.3</version>
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
    ksp("com.bettermile:address-template-processor:0.4.3")
}
```

Gradle (Groovy) additions

```groovy
plugins {
    id 'com.google.devtools.ksp' version '<CURRENT_KSP_VERSION>'
}

dependencies {
    ksp 'com.bettermile:address-template-processor:0.4.3'
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

### License

This project is licensed under the Apache 2.0. See the [LICENSE](LICENSE.txt) for details.

### Acknowledgements

- [OpenCage](https://github.com/OpenCageData/address-formatting/)
- [Java implementation](https://github.com/placemarkt/address-formatter-java)
- [Javascript implementation](https://github.com/fragaria/address-formatter)
