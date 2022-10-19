package com.bettermile.betterroute.addressformatter.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import net.placemarkt.AddressFormatter

class MainActivity : ComponentActivity() {
    private val addressFormatter = AddressFormatter(false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        val components = remember {
                            mutableStateMapOf(
                                "country" to "",
                                "street" to "",
                                "houseNumber" to "",
                                "postalCode" to "",
                                "city" to "",
                            )
                        }
                        components.forEach { (key, value) ->
                            Row {
                                Text(text = "${key.capitalize(LocaleList.current)}:")
                                TextField(value = value, onValueChange = { components[key] = it })
                            }
                        }
                        Text(text = "Formatted:")
                        Text(text = addressFormatter.format(components.toJsonString(), "DE"))
                    }
                }
            }
        }
    }
}

private fun Map<String, String>.toJsonString(): String =
    entries.joinToString(prefix = "{", separator = ",", postfix = "}") { (key, value) ->
        """"$key":"$value""""
    }
