package com.jey.feature.addplant.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jey.core.common.ui.components.PlantTextField

@Composable
fun AddPlantScreen(
    modifier: Modifier = Modifier,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AddPlantScreenContent(
        modifier = modifier,
        plantName = name,
        onPlantNameChanges = { name = it },
        description = description,
        onDescriptionChange = { description = it }
    )
}

@Composable
fun AddPlantScreenContent(
    modifier: Modifier = Modifier,
    plantName: String,
    onPlantNameChanges: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PlantTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Plant Name",
            value = plantName,
            onValueChange = onPlantNameChanges
        )

        PlantTextField(
            modifier = Modifier.fillMaxWidth(),
            label = "Description",
            value = description,
            onValueChange = onDescriptionChange,
            maximumLines = 5
        )
    }
}