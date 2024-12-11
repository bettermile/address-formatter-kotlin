package com.bettermile.addressformatter.mustache

internal interface MustacheLambda

internal expect fun MustacheLambda(lambda: (String) -> String): MustacheLambda
