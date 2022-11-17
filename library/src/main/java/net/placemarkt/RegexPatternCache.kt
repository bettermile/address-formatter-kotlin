package net.placemarkt

internal class RegexPatternCache {
    private val map: MutableMap<String, Regex> = hashMapOf()

    operator fun get(key: String): Regex = map.computeIfAbsent(key, ::Regex)
}
