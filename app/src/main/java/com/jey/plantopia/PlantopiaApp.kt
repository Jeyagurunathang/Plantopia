package com.jey.plantopia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jey.core.common.ui.components.PlantFab
import com.jey.core.common.ui.components.TopAppBar
import com.jey.feature.addplant.api.AddPlantNavKey
import com.jey.feature.addplant.impl.addPlantEntryProvider
import com.jey.feature.home.api.HomeNavKey
import com.jey.feature.home.impl.homeEntryProvider

@Composable
fun PlantopiaApp(modifier: Modifier = Modifier) {
    val plantopiaBackstack = rememberNavBackStack(HomeNavKey)

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopAppBar() },
        floatingActionButton = { PlantFab(
            contentDescription = "Add Plant",
            onClick = { plantopiaBackstack.add(AddPlantNavKey(1)) }
        ) }
    ) { innerPadding ->
        NavDisplay(
            backStack = plantopiaBackstack,
            onBack = { plantopiaBackstack.removeLastOrNull() },
            entryProvider = entryProvider {
                homeEntryProvider()
                addPlantEntryProvider()
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}