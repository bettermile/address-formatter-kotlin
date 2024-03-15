package net.placemarkt

internal data class Name(
    val default: String,
    val alternative: String? = null,
    val alternativesByLanguage: Map<String, String> = emptyMap(),
)
