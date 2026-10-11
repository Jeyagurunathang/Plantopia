package com.jey.feature.addplant.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class AddPlantNavKey (
    val plantId: Int
) : NavKey