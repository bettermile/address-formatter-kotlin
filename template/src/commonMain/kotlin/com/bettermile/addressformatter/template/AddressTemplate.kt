package com.bettermile.addressformatter.template

interface AddressTemplate {
    fun execute(context: Map<String, String>): String
}
