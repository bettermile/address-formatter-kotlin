package com.bettermile.addressformatter.template

@Repeatable
@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.SOURCE)
annotation class AddressTemplateDefinition(val value: String, val propertyName: String = "")
