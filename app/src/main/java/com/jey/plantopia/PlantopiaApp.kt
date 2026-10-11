package com.jey.plantopia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
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
    val currentDestination = plantopiaBackstack.last()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = currentScreenName(currentDestination),
                navIcon = topAppBarNavIcon(currentDestination),
                onBack = { plantopiaBackstack.removeLastOrNull() }
            )
        },
        floatingActionButton = {
            if (currentDestination != AddPlantNavKey) {
                PlantFab(
                    contentDescription = "Add Plant",
                    onClick = { plantopiaBackstack.add(AddPlantNavKey) }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = plantopiaBackstack,
            onBack = { plantopiaBackstack.removeLastOrNull() },
            entryProvider = entryProvider {
                homeEntryProvider()
                addPlantEntryProvider(onBack = { plantopiaBackstack.removeLastOrNull() })
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}

fun currentScreenName(currentDestination: NavKey) : String {
    return when (currentDestination) {
        is AddPlantNavKey -> "Add Plant"
        else -> "Plantopia"
    }
}

fun topAppBarNavIcon(currentDestination: NavKey) : Int {
    return when (currentDestination) {
        is AddPlantNavKey -> R.drawable.back_arrow
        else -> R.drawable.menu
    }
}