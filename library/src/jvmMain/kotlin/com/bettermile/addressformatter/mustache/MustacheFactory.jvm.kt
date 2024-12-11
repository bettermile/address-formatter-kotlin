package com.bettermile.addressformatter.mustache

import com.github.mustachejava.DefaultMustacheFactory
import java.io.StringReader
import java.io.StringWriter
import com.github.mustachejava.Mustache as JMustache
import com.github.mustachejava.MustacheFactory as JMustacheFactory

internal actual fun MustacheFactory(): MustacheFactory = JvmMustacheFactory()

internal class JvmMustacheFactory(private val factory: JMustacheFactory = DefaultMustacheFactory()) : MustacheFactory {

    override fun compile(template: String): Mustache = JvmMustache(factory.compile(StringReader(template), "example"))
}

internal class JvmMustache(private val mustache: JMustache) : Mustache {

    override fun execute(context: List<Any>): String = mustache.execute(StringWriter(), context).toString()
}
