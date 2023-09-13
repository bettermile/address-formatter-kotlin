package net.placemarkt

internal class RegexPatternCache {
    private val map: MutableMap<String, Regex> = hashMapOf()

    operator fun get(
        key: String,
        vararg regexOptions: RegexOption,
        ): Regex = map.computeIfAbsent(key) { Regex(it, regexOptions.toSet()) }
}
