package com.bettermile.addressformatter.mustache

internal interface Mustache {

    fun execute(context: List<Any>): String
}
