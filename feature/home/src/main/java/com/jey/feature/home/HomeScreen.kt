package com.jey.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jey.core.common.ui.components.PlantCard
import com.jey.core.model.Plant

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = hiltViewModel<HomeScreenViewModel>()
    val uiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    when (val state = uiState) {
        HomeScreenUiState.Loading -> {}
        is HomeScreenUiState.Success -> {
            HomeScreenContent(
                modifier = modifier,
                plants = state.plants
            )
        }
        is HomeScreenUiState.Error -> {}
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    plants: List<Plant>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
//        items(count = 10) {
//            PlantCard(
//                plantImage = R.drawable.aloe_vera,
//                plantName = "Aloe Vera"
//            )
//        }

        items(
            plants,
            key = { plant -> plant.id }
        ) { plant ->
            PlantCard(
                plantImage = R.drawable.aloe_vera,
                plantName = plant.name,
                plantDescription = plant.description
            )
        }
    }
}