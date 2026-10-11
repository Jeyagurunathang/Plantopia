package com.jey.feature.addplant.impl

import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jey.feature.addplant.api.AddPlantNavKey

fun EntryProviderScope<NavKey>.addPlantEntryProvider() {
    entry<AddPlantNavKey> {
        AddPlantScreen(modifier = Modifier)
    }
}