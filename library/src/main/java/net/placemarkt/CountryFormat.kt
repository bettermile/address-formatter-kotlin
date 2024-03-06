package net.placemarkt

internal data class CountryFormat(
    val addressTemplate: String? = null,
    val fallbackTemplate: String? = null,
    val replace: List<Replace> = emptyList(),
    val postformatReplace: List<Replace> = emptyList(),
    val useCountry: String? = null,
    val changeCountry: String? = null,
    val addComponent: String? = null,
) {
    data class Replace(
        val search: String,
        val replacement: String,
    )
}
