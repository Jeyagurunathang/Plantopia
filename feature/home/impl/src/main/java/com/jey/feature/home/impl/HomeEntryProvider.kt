package com.jey.feature.home.impl

import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.jey.feature.home.api.HomeNavKey

fun EntryProviderScope<NavKey>.homeEntryProvider() {
    entry<HomeNavKey> {
        HomeScreen(modifier = Modifier)
    }
}