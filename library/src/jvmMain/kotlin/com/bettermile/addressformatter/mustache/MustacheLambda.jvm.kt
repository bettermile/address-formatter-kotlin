package com.bettermile.addressformatter.mustache

import java.util.function.Function

internal actual fun MustacheLambda(lambda: (String) -> String): MustacheLambda {
    return object : MustacheLambda, Function<String, String> {
        override fun apply(t: String): String = lambda(t)
    }
}
