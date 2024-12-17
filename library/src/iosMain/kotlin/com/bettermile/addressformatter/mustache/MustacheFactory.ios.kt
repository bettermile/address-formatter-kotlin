@file:OptIn(ExperimentalForeignApi::class)

package com.bettermile.addressformatter.mustache

import cocoapods.GRMustache.GRMustacheTemplate
import kotlinx.cinterop.ExperimentalForeignApi

internal actual fun MustacheFactory(): MustacheFactory {
    return object : MustacheFactory {
        override fun compile(template: String): Mustache =
            IosMustache(checkNotNull(GRMustacheTemplate.templateFromString(template, null)))
    }
}

private class IosMustache(private val template: GRMustacheTemplate) : Mustache {
    override fun execute(context: List<Any>): String {
        val list = listOf(
            context.first(),
            @Suppress("UNCHECKED_CAST")
            mapOf("first" to (context.last() as Map<String, IosMustacheLambda>).getValue("first").protocol),
        )
        return checkNotNull(template.renderObjectsFromArray(list, null))
    }
}
