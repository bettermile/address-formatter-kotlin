/*
 * Copyright 2022 GLS eComLab
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bettermile.betterroute.addressformatter.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.dp
import net.placemarkt.AddressFormatter

class MainActivity : ComponentActivity() {
    private val addressFormatter = AddressFormatter(false, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                ) {
                    Column(
                        modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        val components = rememberSavableStateMap(
                            "country_code" to "",
                            "street" to "",
                            "houseNumber" to "",
                            "postcode" to "",
                            "city" to "",
                        )
                        components.forEach { (key, value) ->
                            Row(verticalAlignment = CenterVertically) {
                                Text(text = "${key.capitalize(LocaleList.current)}:")
                                TextField(value = value, onValueChange = { components[key] = it })
                            }
                        }
                        Text(text = "Formatted:")
                        Text(text = addressFormatter.format(components, "DE"))
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberSavableStateMap(
    vararg initialEntries: Pair<String, String>
): SnapshotStateMap<String, String> {
    return rememberSaveable(
        saver = mapSaver(
            save = { it },
            restore = {
                mutableStateMapOf<String, String>().apply {
                    @Suppress("UNCHECKED_CAST")
                    putAll(it as Map<String, String>)
                }
            }
        ),
    ) {
        mutableStateMapOf(*initialEntries)
    }
}
