package com.example

import com.bettermile.addressformatter.template.AddressTemplate
import kotlin.OptIn
import kotlin.String
import kotlin.collections.Map

@OptIn(com.bettermile.addressformatter.template.InternalForInheritanceAddressFormatterApi::class)
internal object AddressTemplates {
  public val test1: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        append("test1")
      }
    }

  public val test2: AddressTemplate
    get() = object : AddressTemplate {
      override fun render(context: Map<String, String>): String = buildString {
        append("test2")
      }
    }
}
