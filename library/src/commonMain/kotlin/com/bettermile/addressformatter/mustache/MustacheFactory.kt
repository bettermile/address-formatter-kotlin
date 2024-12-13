package com.bettermile.addressformatter.mustache

internal interface MustacheFactory {

    fun compile(template: String): Mustache
}

internal expect fun MustacheFactory(): MustacheFactory
