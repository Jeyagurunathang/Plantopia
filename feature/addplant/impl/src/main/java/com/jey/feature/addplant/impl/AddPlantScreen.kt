package com.jey.feature.addplant.impl

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jey.core.common.ui.components.PlantTextField

@Composable
fun AddPlantScreen(
    modifier: Modifier = Modifier,
    viewModel: AddPlantScreenViewModel,
    onBack: () -> Unit,
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    AddPlantScreenContent(
        modifier = modifier,
        name = name,
        onNameChanges = { name = it },
        description = description,
        onDescriptionChange = { description = it },
        onAddClicked = viewModel::insertDataIntoDb,
        onBack = onBack
    )
}

@Composable
fun AddPlantScreenContent(
    modifier: Modifier = Modifier,
    name: String,
    onNameChanges: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    onAddClicked: (String, String) -> Unit,
    onBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PlantTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Plant Name",
                value = name,
                onValueChange = onNameChanges
            )

            PlantTextField(
                modifier = Modifier.fillMaxWidth(),
                label = "Description",
                value = description,
                onValueChange = onDescriptionChange,
                maximumLines = 5
            )
        }

        Button(
            onClick = {
                onAddClicked(name, description)
                onBack()
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Icon(
                painter = painterResource(R.drawable.correct),
                contentDescription = "Add Plant"
            )
        }
    }
}