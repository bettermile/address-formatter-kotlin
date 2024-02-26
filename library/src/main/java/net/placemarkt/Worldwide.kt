package net.placemarkt

internal object Workldwide {
private const val generic1 = """{{{attention}}}
{{{house}}}
{{#first}} {{{road}}} || {{{place}}} || {{{hamlet}}} {{/first}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{village}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{archipelago}}}
{{{country}}}
"""
private const val generic2 = """{{{attention}}}
{{#first}} {{{house}}}, {{{quarter}}} || {{{house}}} {{/first}}
{{{house_number}}} {{{road}}}
{{#first}} {{{village}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} {{/first}} {{{postcode}}}
{{#first}} {{{country}}} || {{{state}}} {{/first}}
"""
private const val generic3 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{place}}}
{{{postcode}}} {{#first}} {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{city}}} || {{{municipality}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic4 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{suburb}}} || {{{municipality}}} || {{{county}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
"""
private const val generic5 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic6 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{{county}}}
{{{state}}}
{{{country}}}
"""
private const val generic7 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}}{{/first}}, {{{postcode}}}
{{{country}}}
"""
private const val generic8 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}} {{#first}} {{{county_code}}} || {{{county}}} {{/first}}
{{{country}}}
"""
private const val generic9 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic10 = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{state}}}
{{{country}}}
{{{postcode}}}
"""
private const val generic11 = """{{{country}}}
{{{state}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{suburb}}}
{{{road}}}, {{{house_number}}}
{{{house}}}
{{{attention}}}
"""
private const val generic12 = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} - {{{postcode}}}
{{{state}}}
{{{country}}}
"""
private const val generic13 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} || {{{region}}} {{/first}} {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
"""
private const val generic14 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state_district}}} {{/first}}
{{{state}}}
{{{country}}}
"""
private const val generic15 = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{state}}} || {{{county}}} {{/first}}
{{{country}}}
"""
private const val generic16 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic17 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic18 = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic19 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{country}}}
"""
private const val generic20 = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{country}}}
"""
private const val generic21 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic22 = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{country}}}
"""
private const val generic23 = """{{{attention}}}
{{house}}
{{{house_number}}} {{{road}}}
{{quarter}}
{{#first}} {{{village}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} {{/first}}
{{{postcode}}}
{{#first}} {{{country}}} || {{{state}}} {{/first}}
"""
private const val fallback1 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{place}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} || {{{island}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{#first}} {{{county}}} || {{{state_district}}} || {{{state}}} || {{{region}}} || {{{island}}}, {{{archipelago}}} {{/first}}
{{{country}}}
"""
private const val fallback2 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{place}}}
{{#first}} {{{suburb}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{municipality}}} || {{{county}}} || {{{island}}} || {{{state_district}}} {{/first}}, {{#first}} {{{state}}} || {{{state_code}}} {{/first}}
{{{country}}}
"""
private const val fallback3 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{place}}}
{{#first}} {{{suburb}}} || {{{island}}} {{/first}}
{{#first}} {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{#first}} {{{town}}} || {{{city}}}{{/first}}
{{{county}}}
{{#first}} {{{state}}} || {{{state_code}}} {{/first}}
{{{country}}}
"""
private const val fallback4 = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{place}}}
{{{suburb}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{county}}} {{/first}}
{{#first}} {{{state}}} || {{{county}}} {{/first}}
{{{country}}}
"""
val default =     CountryFormat(
        addressTemplate = generic1,
        fallbackTemplate = fallback1,
    )
val countries: Map<String, Lazy<CountryFormat>> = mapOf(
"AD" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"AE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"AF" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"AG" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"AI" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{postcode}}} {{{country}}}
""",
    )
},
"AL" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{city_district}}} || {{{municipality}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{4}) ([^,]*)\n", replacement = "\n$1-$2\n"),
        ),
    )
},
"AM" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"AO" to lazy {
    CountryFormat(
        addressTemplate = generic7,
    )
},
"AQ" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{country}}} || {{{continent}}} {{/first}}
""",
    )
},
"AR" to lazy {
    CountryFormat(
        addressTemplate = generic9,
        replace = listOf(
            CountryFormat.Replace(search = "^Autonomous City of ", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\w\\d{4})(\\w{3}) ", replacement = "\n$1 $2 "),
        ),
    )
},
"AS" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=American Samoa",
    )
},
"AT" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"AU" to lazy {
    CountryFormat(
        addressTemplate = generic13,
    )
},
"AW" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"AX" to lazy {
    CountryFormat(
        useCountry = "FI",
        changeCountry = "Åland, Finland",
    )
},
"AZ" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"BA" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"BB" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"BD" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} - {{{postcode}}}
{{{country}}}
""",
    )
},
"BE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{village}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{archipelago}}}
{{{country}}}
""",
    )
},
"BF" to lazy {
    CountryFormat(
        addressTemplate = generic6,
    )
},
"BG" to lazy {
    CountryFormat(
        addressTemplate = generic19,
    )
},
"BH" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"BI" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"BJ" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"BL" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Saint-Barthélemy, France",
    )
},
"BM" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"BN" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{#first}} {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"BO" to lazy {
    CountryFormat(
        addressTemplate = generic17,
        replace = listOf(
            CountryFormat.Replace(search = "^Municipio Nuestra Senora de ", replacement = ""),
        ),
    )
},
"BQ" to lazy {
    CountryFormat(
        useCountry = "NL",
        changeCountry = "Caribbean Netherlands",
    )
},
"BR" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{village}}} || {{hamlet}}{{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} {{/first}} - {{#first}} {{{state_code}}} || {{{state}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\\b(\\d{5})(\\d{3})\\b", replacement = "$1-$2"),
        ),
    )
},
"BS" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{{county}}}
{{{country}}}
""",
    )
},
"BT" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}, {{{house}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"BV" to lazy {
    CountryFormat(
        useCountry = "NO",
        changeCountry = "Bouvet Island, Norway",
    )
},
"BW" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"BY" to lazy {
    CountryFormat(
        addressTemplate = generic11,
    )
},
"BZ" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"CA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{house_number}}} {{{road}}} || {{{suburb}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state_district}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        fallbackTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{house_number}}} {{{road}}} || {{{suburb}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state_district}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " ([A-Za-z]{2}) ([A-Za-z]\\d[A-Za-z])(\\d[A-Za-z]\\d)\n", replacement = " $1 $2 $3\n"),
        ),
    )
},
"CA_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{house_number}}} {{{road}}} || {{{suburb}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state_district}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        fallbackTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{house_number}}} {{{road}}} || {{{suburb}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state_district}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " ([A-Za-z]{2}) ([A-Za-z]\\d[A-Za-z])(\\d[A-Za-z]\\d)\n", replacement = " $1 $2 $3\n"),
        ),
    )
},
"CA_fr" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{house_number}}}, {{{road}}} || {{{suburb}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state_district}}} {{/first}} {{#first}} ({{{state_code}}}) || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " ([A-Za-z]{2}) ([A-Za-z]\\d[A-Za-z])(\\d[A-Za-z]\\d)\n", replacement = " $1 $2 $3\n"),
        ),
    )
},
"CC" to lazy {
    CountryFormat(
        useCountry = "AU",
        changeCountry = "Australia",
    )
},
"CD" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"CF" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"CG" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"CH" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{village}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{country}}}
""",
        replace = listOf(
            CountryFormat.Replace(search = "Verwaltungskreis", replacement = ""),
            CountryFormat.Replace(search = "Verwaltungsregion", replacement = ""),
            CountryFormat.Replace(search = " administrative district", replacement = ""),
            CountryFormat.Replace(search = " administrative region", replacement = ""),
        ),
    )
},
"CI" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"CK" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"CL" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{village}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{region}}}
{{{country}}}
""",
    )
},
"CM" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"CN" to lazy {
    CountryFormat(
        addressTemplate = """{{{postcode}}} {{{country}}}
{{#first}} {{{state_code}}} || {{{state}}} || {{{state_district}}} || {{{region}}}{{/first}}
{{{county}}}
{{#first}}{{{city}}} || {{{town}}} || {{{municipality}}}|| {{{village}}}|| {{{hamlet}}}{{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}} {{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"CN_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{county}}}
{{#first}}{{{city}}} || {{{town}}} || {{{municipality}}}|| {{{village}}}|| {{{hamlet}}}{{/first}}
{{#first}} {{{state_code}}} || {{{state}}} || {{{state_district}}} || {{{region}}}{{/first}}
{{{country}}} {{{postcode}}}
""",
    )
},
"CN_zh" to lazy {
    CountryFormat(
        addressTemplate = """{{{postcode}}} {{{country}}}
{{#first}} {{{state_code}}} || {{{state}}} || {{{state_district}}} || {{{region}}}{{/first}}
{{{county}}}
{{#first}}{{{city}}} || {{{town}}} || {{{municipality}}}|| {{{village}}}|| {{{hamlet}}}{{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}} {{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"CO" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Localidad ", replacement = " "),
            CountryFormat.Replace(search = "Bogota, Bogota", replacement = "Bogota"),
            CountryFormat.Replace(search = "Bogota, Bogotá Distrito Capital", replacement = "Bogota"),
            CountryFormat.Replace(search = "Bogotá, Bogotá Distrito Capital", replacement = "Bogotá"),
        ),
    )
},
"CR" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{state}}}, {{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}} {{{country}}}
""",
    )
},
"CU" to lazy {
    CountryFormat(
        addressTemplate = generic7,
    )
},
"CV" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{4}) ([^,]*)\n", replacement = "\n$1-$2\n"),
        ),
    )
},
"CW" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"CX" to lazy {
    CountryFormat(
        useCountry = "AU",
        changeCountry = "Australia",
        addComponent = "state=Christmas Island",
    )
},
"CY" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"CZ" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{3})(\\d{2}) ", replacement = "\n$1 $2 "),
        ),
    )
},
"DE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{road}}} || {{{place}}} || {{{hamlet}}} {{/first}} {{{house_number}}}
{{{postcode}}} {{{village}}} {{#first}} {{{postal_city}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{archipelago}}}
{{{country}}}    
""",
        fallbackTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{road}}} || {{{place}}} || {{{hamlet}}} {{/first}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{town}}} || {{{city}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{county}}} {{/first}}
{{#first}} {{{state}}} || {{{state_district}}} {{/first}}
{{{country}}}
""",
        replace = listOf(
            CountryFormat.Replace(search = "^Stadtteil ", replacement = ""),
            CountryFormat.Replace(search = "^Stadtbezirk (\\d+)", replacement = ""),
            CountryFormat.Replace(search = "^Ortsbeirat (\\d+) :", replacement = ""),
            CountryFormat.Replace(search = "^Gemeinde ", replacement = ""),
            CountryFormat.Replace(search = "^Gemeindeverwaltungsverband ", replacement = ""),
            CountryFormat.Replace(search = "^Landkreis ", replacement = ""),
            CountryFormat.Replace(search = "^Kreis ", replacement = ""),
            CountryFormat.Replace(search = "^Grenze ", replacement = ""),
            CountryFormat.Replace(search = "^Free State of ", replacement = ""),
            CountryFormat.Replace(search = "^Freistaat ", replacement = ""),
            CountryFormat.Replace(search = "^Regierungsbezirk ", replacement = ""),
            CountryFormat.Replace(search = "^Stadtgebiet ", replacement = ""),
            CountryFormat.Replace(search = "^Gemeindefreies Gebiet ", replacement = ""),
            CountryFormat.Replace(search = "city=Alt-Berlin", replacement = "Berlin"),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Berlin\nBerlin", replacement = "Berlin"),
            CountryFormat.Replace(search = "Bremen\nBremen", replacement = "Bremen"),
            CountryFormat.Replace(search = "Hamburg\nHamburg", replacement = "Hamburg"),
        ),
    )
},
"DJ" to lazy {
    CountryFormat(
        addressTemplate = generic16,
        replace = listOf(
            CountryFormat.Replace(search = "city=Djibouti", replacement = "Djibouti-Ville"),
        ),
    )
},
"DK" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        replace = listOf(
            CountryFormat.Replace(search = "^Capital Region of ", replacement = ""),
        ),
    )
},
"DM" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"DO" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{{state}}}
{{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = ", Distrito Nacional", replacement = ", DN"),
        ),
    )
},
"DZ" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"EC" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"EG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"EE" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"EH" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"ER" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"ES" to lazy {
    CountryFormat(
        addressTemplate = generic15,
        fallbackTemplate = fallback4,
        replace = listOf(
            CountryFormat.Replace(search = "Autonomous Community of the", replacement = ""),
            CountryFormat.Replace(search = "Autonomous Community of", replacement = ""),
            CountryFormat.Replace(search = "^Community of ", replacement = ""),
        ),
    )
},
"ET" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"FI" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"FJ" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"FK" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "Falkland Islands, United Kingdom",
    )
},
"FM" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=Micronesia",
    )
},
"FO" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Territorial waters of Faroe Islands", replacement = "Faroe Islands"),
        ),
    )
},
"FR" to lazy {
    CountryFormat(
        addressTemplate = generic3,
        replace = listOf(
            CountryFormat.Replace(search = "Polynésie française, Îles du Vent \\(eaux territoriales\\)", replacement = "Polynésie française"),
            CountryFormat.Replace(search = "France, Mayotte \\(eaux territoriales\\)", replacement = "Mayotte, France"),
            CountryFormat.Replace(search = "France, La Réunion \\(eaux territoriales\\)", replacement = "La Réunion, France"),
            CountryFormat.Replace(search = "Grande Terre et récifs d'Entrecasteaux", replacement = ""),
            CountryFormat.Replace(search = "France, Nouvelle-Calédonie", replacement = "Nouvelle-Calédonie, France"),
            CountryFormat.Replace(search = "\\(eaux territoriales\\)", replacement = ""),
            CountryFormat.Replace(search = "state= \\(France\\)$", replacement = ""),
            CountryFormat.Replace(search = "Paris (\\d+)(\\w+) Arrondissement$", replacement = "Paris"),
        ),
    )
},
"GA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{municipality}}} || {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"GB" to lazy {
    CountryFormat(
        addressTemplate = generic23,
        fallbackTemplate = fallback3,
        replace = listOf(
            CountryFormat.Replace(search = "village= CP$", replacement = ""),
            CountryFormat.Replace(search = "^Borough of ", replacement = ""),
            CountryFormat.Replace(search = "^County( of)? ", replacement = ""),
            CountryFormat.Replace(search = "^Parish of ", replacement = ""),
            CountryFormat.Replace(search = "^Greater London", replacement = "London"),
            CountryFormat.Replace(search = "^London Borough of ", replacement = ""),
            CountryFormat.Replace(search = "Royal Borough of ", replacement = ""),
            CountryFormat.Replace(search = "County Borough of ", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "London, London", replacement = "London"),
            CountryFormat.Replace(search = "London, Greater London", replacement = "London"),
            CountryFormat.Replace(search = "City of Westminster", replacement = "London"),
            CountryFormat.Replace(search = "City of Nottingham", replacement = "Nottingham"),
            CountryFormat.Replace(search = ", United Kingdom$", replacement = "\nUnited Kingdom"),
            CountryFormat.Replace(search = "London\nEngland\nUnited Kingdom", replacement = "London\nUnited Kingdom"),
        ),
    )
},
"GD" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"GE" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"GF" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "France",
    )
},
"GG" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "Guernsey, Channel Islands",
    )
},
"GH" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"GI" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"GL" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"GM" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"GN" to lazy {
    CountryFormat(
        addressTemplate = generic14,
    )
},
"GP" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Guadeloupe, France",
    )
},
"GQ" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"GR" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        replace = listOf(
            CountryFormat.Replace(search = "Municipal Unit of ", replacement = ""),
            CountryFormat.Replace(search = "Regional Unit of ", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{3})(\\d{2}) ", replacement = "\n$1 $2 "),
        ),
    )
},
"GS" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "United Kingdom",
        addComponent = "county=South Georgia",
    )
},
"GT" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}}-{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} || {{{state}}} {{/first}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{5})- ", replacement = "\n$1-"),
            CountryFormat.Replace(search = "\n -", replacement = "\n"),
        ),
    )
},
"GU" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=Guam",
    )
},
"GW" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"GY" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"HK" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{state_district}}}
{{#first}} {{{state}}} || {{{country}}} {{/first}}
""",
    )
},
"HK_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{state_district}}}
{{{state}}}
{{{country}}}
""",
    )
},
"HK_zh" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{state}}}
{{{state_district}}}
{{{road}}}
{{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"HM" to lazy {
    CountryFormat(
        useCountry = "AU",
        changeCountry = "Australia",
        addComponent = "state=Heard Island and McDonald Islands",
    )
},
"HN" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"HR" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"HT" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = " Commune de", replacement = " "),
        ),
    )
},
"HU" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{road}}} {{{house_number}}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"ID" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{state}}}
{{{country}}}
""",
    )
},
"IE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{{county}}}
{{{postcode}}}
{{{country}}}
""",
        replace = listOf(
            CountryFormat.Replace(search = " City$", replacement = ""),
            CountryFormat.Replace(search = "The Municipal District of ", replacement = ""),
            CountryFormat.Replace(search = "The Metropolitan District of ", replacement = ""),
            CountryFormat.Replace(search = "Municipal District", replacement = ""),
            CountryFormat.Replace(search = "Electoral Division", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Dublin\nCounty Dublin", replacement = "Dublin"),
            CountryFormat.Replace(search = "Dublin\nLeinster", replacement = "Dublin"),
            CountryFormat.Replace(search = "Galway\nCounty Galway", replacement = "Galway"),
            CountryFormat.Replace(search = "Kilkenny\nCounty Kilkenny", replacement = "Kilkenny"),
            CountryFormat.Replace(search = "Limerick\nCounty Limerick", replacement = "Limerick"),
            CountryFormat.Replace(search = "Tipperary\nCounty Tipperary", replacement = "Tipperary"),
            CountryFormat.Replace(search = "\n(([AC-FHKNPRTV-Y][0-9]{2}|D6W))[ -]?([0-9AC-FHKNPRTV-Y]{4})", replacement = "\n$1 $3"),
        ),
    )
},
"IL" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"IM" to lazy {
    CountryFormat(
        useCountry = "GB",
    )
},
"IN" to lazy {
    CountryFormat(
        addressTemplate = generic12,
    )
},
"IO" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "British Indian Ocean Territory, United Kingdom",
    )
},
"IQ" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{#first}} {{{city_district}}} || {{{neighbourhood}}} || {{{suburb}}} {{/first}}
{{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"IR" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{#first}}{{{province}}} || {{{state}}} || {{{state_district}}}{{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"IR_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{#first}}{{{state}}} || {{{state_district}}}{{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"IR_fa" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{state}}}
{{{state_district}}}
{{#first}} {{{state}}} || {{{province}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{{house}}}
{{{attention}}}
{{{postcode}}}
""",
    )
},
"IS" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"IT" to lazy {
    CountryFormat(
        addressTemplate = generic8,
        replace = listOf(
            CountryFormat.Replace(search = "Città metropolitana di ", replacement = ""),
            CountryFormat.Replace(search = "Metropolitan City of ", replacement = ""),
            CountryFormat.Replace(search = "^Provincia di ", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Vatican City\nVatican City$", replacement = "\nVatican City"),
            CountryFormat.Replace(search = "Città del Vaticano\nCittà del Vaticano$", replacement = "Città del Vaticano\n"),
        ),
    )
},
"JE" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "Jersey, Channel Islands",
    )
},
"JM" to lazy {
    CountryFormat(
        addressTemplate = generic20,
    )
},
"JO" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"JP" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{state}}} || {{{state_district}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " (\\d{3})(\\d{4})\n", replacement = " $1-$2\n"),
        ),
    )
},
"JP_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{state}}} || {{{state_district}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " (\\d{3})(\\d{4})\n", replacement = " $1-$2\n"),
        ),
    )
},
"JP_ja" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{postcode}}}
{{#first}} {{{state}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{{house}}}
{{{attention}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = " (\\d{3})(\\d{4})\n", replacement = " $1-$2\n"),
        ),
    )
},
"KE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"KG" to lazy {
    CountryFormat(
        addressTemplate = generic11,
    )
},
"KH" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"KI" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"KM" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{country}}}
""",
    )
},
"KN" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{state}}} || {{{island}}} {{/first}}
{{{country}}}
""",
    )
},
"KP" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"KR" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{state}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}, {{{road}}} {{{house_number}}}
{{{attention}}}
{{{postcode}}}
""",
    )
},
"KR_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}, {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{/first}} {{{postcode}}}
{{{state}}}
{{{country}}}
""",
    )
},
"KR_ko" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{state}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}, {{{road}}} {{{house_number}}}
{{{attention}}}
{{{postcode}}}
""",
    )
},
"KW" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}

{{{road}}}
{{{house_number}}} {{{house}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"KY" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"KZ" to lazy {
    CountryFormat(
        addressTemplate = generic11,
    )
},
"LA" to lazy {
    CountryFormat(
        addressTemplate = generic22,
    )
},
"LB" to lazy {
    CountryFormat(
        addressTemplate = generic2,
        postformatReplace = listOf(
            CountryFormat.Replace(search = " (\\d{4}) (\\d{4})\n", replacement = " $1 $2\n"),
        ),
    )
},
"LC" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"LI" to lazy {
    CountryFormat(
        useCountry = "CH",
    )
},
"LK" to lazy {
    CountryFormat(
        addressTemplate = generic20,
    )
},
"LR" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"LS" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"LT" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"LU" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"LV" to lazy {
    CountryFormat(
        addressTemplate = generic7,
    )
},
"LY" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"MA" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"MC" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"MD" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"ME" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"MF" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "France",
    )
},
"MH" to lazy {
    CountryFormat(
        useCountry = "US",
        addComponent = "state=Marshall Islands",
    )
},
"MG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"MK" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"ML" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"MM" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}, {{{postcode}}}
{{{country}}}
""",
    )
},
"MN" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{city_district}}}
{{#first}} {{{suburb}}} || {{{neighbourhood}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"MO" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{village}}} || {{{hamlet}}} || {{{state_district}}} {{/first}}
{{{country}}}
""",
    )
},
"MO_pt" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{village}}} || {{{hamlet}}} || {{{state_district}}} {{/first}}
{{{country}}}
""",
    )
},
"MO_zh" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{#first}} {{{suburb}}} || {{{village}}} || {{{hamlet}}} || {{{state_district}}} {{/first}}
{{{road}}}
{{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"MP" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=Northern Mariana Islands",
    )
},
"MS" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"MT" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{suburb}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"MQ" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Martinique, France",
    )
},
"MR" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"MU" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"MV" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"MW" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"MX" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"MY" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{state}}}
{{{country}}}
""",
    )
},
"MZ" to lazy {
    CountryFormat(
        addressTemplate = generic15,
        fallbackTemplate = fallback4,
    )
},
"NA" to lazy {
    CountryFormat(
        addressTemplate = generic2,
    )
},
"NC" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Nouvelle-Calédonie, France",
    )
},
"NE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}}
{{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{country}}}
""",
    )
},
"NF" to lazy {
    CountryFormat(
        useCountry = "AU",
        changeCountry = "Australia",
        addComponent = "state=Norfolk Island",
    )
},
"NG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{state}}}
{{{country}}}
""",
    )
},
"NI" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"NL" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{4})(\\w{2}) ", replacement = "\n$1 $2 "),
            CountryFormat.Replace(search = "\nKoninkrijk der Nederlanden$", replacement = "\nNederland"),
        ),
    )
},
"NO" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"NP" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{neighbourhood}}} || {{{city}}} {{/first}}
{{#first}} {{{municipality}}} || {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"NR" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"NU" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"NZ" to lazy {
    CountryFormat(
        addressTemplate = generic20,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Wellington\nWellington City", replacement = "Wellington"),
        ),
    )
},
"OM" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{state}}}
{{{country}}}
""",
    )
},
"PA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{state}}}
{{{country}}}
""",
        replace = listOf(
            CountryFormat.Replace(search = "city=Panama$", replacement = "Panama City"),
            CountryFormat.Replace(search = "city=Panamá$", replacement = "Ciudad de Panamá"),
        ),
    )
},
"PE" to lazy {
    CountryFormat(
        addressTemplate = generic19,
    )
},
"PF" to lazy {
    CountryFormat(
        replace = listOf(
            CountryFormat.Replace(search = "Polynésie française, Îles du Vent \\(eaux territoriales\\)", replacement = "Polynésie française"),
        ),
        useCountry = "FR",
        changeCountry = "Polynésie française, France",
    )
},
"PG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}} {{{state}}}
{{{country}}}
""",
    )
},
"PH" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}, {{#first}} {{{suburb}}}, || {{{city_district}}}, || {{{neighbourhood}}}, {{/first}} {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{suburb}}} || {{{state_district}}} {{/first}}
{{{postcode}}} {{#first}} {{{municipality}}} {{{region}}} {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"PK" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"PL" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{2})(\\w{3}) ", replacement = "\n$1-$2 "),
        ),
    )
},
"PM" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Saint-Pierre-et-Miquelon, France",
    )
},
"PN" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{#first}} {{{city}}} || {{{town}}} || {{{island}}} {{/first}}
{{{country}}}
""",
    )
},
"PR" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=Puerto Rico",
    )
},
"PS" to lazy {
    CountryFormat(
        useCountry = "IL",
    )
},
"PT" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{4})(\\d{3}) ", replacement = "\n$1-$2 "),
        ),
    )
},
"PW" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"PY" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"QA" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"RE" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "La Réunion, France",
    )
},
"RO" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"RS" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"RU" to lazy {
    CountryFormat(
        addressTemplate = generic10,
        fallbackTemplate = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} || {{{island}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{municipality}}} {{/first}}
{{#first}} {{{county}}} || {{{state_district}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"RW" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"SA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}, {{#first}} {{{village}}} || {{{hamlet}}} || {{{city_district}}} || {{{suburb}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"SB" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"SC" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{island}}} {{/first}}
{{{island}}}
{{{country}}}
""",
    )
},
"SD" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"SE" to lazy {
    CountryFormat(
        addressTemplate = generic1,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{3})(\\d{2}) ", replacement = "\n$1 $2 "),
        ),
    )
},
"SG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{#first}} {{{house}}}, {{{quarter}}} || {{{house}}} {{/first}}
{{{house_number}}} {{{road}}}, {{{residential}}}
{{#first}} {{{country}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{hamlet}}} || {{{village}}} || {{{county}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"SH" to lazy {
    CountryFormat(
        useCountry = "GB",
        changeCountry = "\$state, United Kingdom",
    )
},
"SI" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"SJ" to lazy {
    CountryFormat(
        useCountry = "NO",
        changeCountry = "Norway",
    )
},
"SK" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} {{#first}} {{{postal_city}}} || {{{city}}} || {{{town}}} || {{{village}}} || {{{municipality}}} || {{{city_district}}} || {{{hamlet}}} || {{{county}}} || {{{state}}} {{/first}}
{{{country}}}
""",
        replace = listOf(
            CountryFormat.Replace(search = "^District of ", replacement = ""),
            CountryFormat.Replace(search = "^Region of ", replacement = ""),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n(\\d{3})(\\d{2}) ", replacement = "\n$1 $2 "),
        ),
    )
},
"SL" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"SM" to lazy {
    CountryFormat(
        useCountry = "IT",
    )
},
"SN" to lazy {
    CountryFormat(
        addressTemplate = generic3,
        replace = listOf(
            CountryFormat.Replace(search = "^Commune de ", replacement = ""),
            CountryFormat.Replace(search = "^Arrondissement de ", replacement = ""),
            CountryFormat.Replace(search = "^Département de ", replacement = ""),
        ),
    )
},
"SO" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"SR" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"SS" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"ST" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"SV" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{{postcode}}} - {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{{state}}}
{{{country}}}
""",
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\n- ", replacement = "\n "),
        ),
    )
},
"SX" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"SY" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{#first}} {{{village}}} || {{{hamlet}}} || {{{city_district}}} || {{{neighbourhood}}} || {{{suburb}}} {{/first}}
{{{postcode}}} {{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{state}}} {{/first}}

{{{country}}}
""",
    )
},
"SZ" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"TC" to lazy {
    CountryFormat(
        addressTemplate = generic23,
        fallbackTemplate = """{{{attention}}}
{{{house_number}}} {{{road}}}
{{quarter}}
{{#first}} {{{village}}} || {{{town}}} || {{{city}}} || {{{municipality}}} || {{{hamlet}}} || {{{county}}} {{/first}}
{{{island}}}
{{{country}}}
""",
    )
},
"TD" to lazy {
    CountryFormat(
        addressTemplate = generic21,
    )
},
"TF" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Terres australes et antarctiques françaises, France",
    )
},
"TG" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"TH" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{#first}} {{{village}}} || {{{hamlet}}} {{/first}}
{{{road}}}
{{#first}} {{{neighbourhood}}} || {{{city}}} || {{{town}}} {{/first}}, {{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{{state}}} {{{postcode}}}
{{{country}}}
""",
    )
},
"TJ" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"TK" to lazy {
    CountryFormat(
        useCountry = "NZ",
        changeCountry = "Tokelau, New Zealand",
    )
},
"TL" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"TM" to lazy {
    CountryFormat(
        addressTemplate = generic22,
    )
},
"TN" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"TO" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"TR" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"TT" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{{postcode}}}
{{{country}}}
""",
    )
},
"TV" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{#first}} {{{county}}} || {{{state_district}}} || {{{state}}} || {{{island}}} {{/first}}
{{{country}}}
""",
    )
},
"TW" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}} {{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}} {{{road}}} {{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"TW_en" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}}, {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}, {{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}
{{{country}}}
""",
    )
},
"TW_zh" to lazy {
    CountryFormat(
        addressTemplate = """{{{country}}}
{{{postcode}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}} {{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}} {{{road}}} {{{house_number}}}
{{{house}}}
{{{attention}}}
""",
    )
},
"TZ" to lazy {
    CountryFormat(
        addressTemplate = generic14,
        fallbackTemplate = generic14,
        postformatReplace = listOf(
            CountryFormat.Replace(search = "Dar es Salaam\nDar es Salaam", replacement = "Dar es Salaam"),
        ),
    )
},
"UA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}}, {{{house_number}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{municipality}}} {{/first}}
{{#first}} {{{region}}} || {{{state}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"UG" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
"UM" to lazy {
    CountryFormat(
        fallbackTemplate = fallback2,
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=US Minor Outlying Islands",
    )
},
"US" to lazy {
    CountryFormat(
        addressTemplate = generic4,
        fallbackTemplate = fallback2,
        replace = listOf(
            CountryFormat.Replace(search = "state=United States Virgin Islands", replacement = "US Virgin Islands"),
            CountryFormat.Replace(search = "state=USVI", replacement = "US Virgin Islands"),
        ),
        postformatReplace = listOf(
            CountryFormat.Replace(search = "\nUS$", replacement = "\nUnited States of America"),
            CountryFormat.Replace(search = "\nUSA$", replacement = "\nUnited States of America"),
            CountryFormat.Replace(search = "\nUnited States$", replacement = "\nUnited States of America"),
            CountryFormat.Replace(search = "Town of ", replacement = ""),
            CountryFormat.Replace(search = "Township of ", replacement = ""),
        ),
    )
},
"UZ" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}
{{#first}} {{{state}}} || {{{state_district}}} {{/first}}
{{{country}}}
{{{postcode}}}
""",
    )
},
"UY" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"VA" to lazy {
    CountryFormat(
        useCountry = "IT",
    )
},
"VC" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"VE" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{road}}} {{{house_number}}}
{{#first}} {{{city}}} || {{{town}}} || {{{state_district}}} || {{{village}}} || {{{hamlet}}} {{/first}} {{{postcode}}}, {{#first}} {{{state_code}}} || {{{state}}} {{/first}}
{{{country}}}
""",
    )
},
"VG" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} {{/first}}, {{{island}}}
{{{country}}}, {{{postcode}}}
""",
    )
},
"VI" to lazy {
    CountryFormat(
        useCountry = "US",
        changeCountry = "United States of America",
        addComponent = "state=US Virgin Islands",
    )
},
"VN" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{neighbourhood}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state_district}}} {{/first}}
{{{state}}} {{{postcode}}}
{{{country}}}
""",
    )
},
"VU" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"WF" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Wallis-et-Futuna, France",
    )
},
"WS" to lazy {
    CountryFormat(
        addressTemplate = generic17,
    )
},
"XC" to lazy {
    CountryFormat(
        addressTemplate = generic6,
    )
},
"XK" to lazy {
    CountryFormat(
        addressTemplate = generic1,
    )
},
"YE" to lazy {
    CountryFormat(
        addressTemplate = generic18,
    )
},
"YT" to lazy {
    CountryFormat(
        useCountry = "FR",
        changeCountry = "Mayotte, France",
    )
},
"ZA" to lazy {
    CountryFormat(
        addressTemplate = """{{{attention}}}
{{{house}}}
{{{house_number}}} {{{road}}}
{{#first}} {{{suburb}}} || {{{city_district}}} || {{{state_district}}} {{/first}}
{{#first}} {{{city}}} || {{{town}}} || {{{village}}} || {{{hamlet}}} || {{{state}}} {{/first}}
{{{postcode}}}
{{{country}}}
""",
    )
},
"ZM" to lazy {
    CountryFormat(
        addressTemplate = generic3,
    )
},
"ZW" to lazy {
    CountryFormat(
        addressTemplate = generic16,
    )
},
)
}
