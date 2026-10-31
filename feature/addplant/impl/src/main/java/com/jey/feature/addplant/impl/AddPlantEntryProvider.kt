package com.jey.feature.addplant.impl

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jey.feature.addplant.api.AddPlantNavKey

fun EntryProviderScope<NavKey>.addPlantEntryProvider(onBack: () -> Unit) {
    entry<AddPlantNavKey> {
        val addPlantScreenViewModel = hiltViewModel<AddPlantScreenViewModel>()

        AddPlantScreen(
            onBack = onBack,
            viewModel = addPlantScreenViewModel
        )
    }
}