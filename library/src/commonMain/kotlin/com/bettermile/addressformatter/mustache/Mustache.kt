package com.bettermile.addressformatter.mustache

internal interface Mustache {

    fun execute(context: Map<String, String>): String
}
