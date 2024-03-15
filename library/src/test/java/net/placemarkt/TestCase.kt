package net.placemarkt

data class TestCase(
    val components: Map<String, String>,
    val expected: String,
    val description: String,
    val fileName: String,
)
