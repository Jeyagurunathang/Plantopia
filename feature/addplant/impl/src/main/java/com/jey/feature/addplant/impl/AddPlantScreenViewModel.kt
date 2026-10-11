package com.jey.feature.addplant.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jey.core.data.repository.PlantRepository
import com.jey.core.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlantScreenViewModel @Inject constructor(
    private val plantRepository: PlantRepository
) : ViewModel() {
    fun insertDataIntoDb(
        name: String,
        description: String,
    ) {
        viewModelScope.launch {
            plantRepository.insertPlant(Plant(name = name, description = description))
        }
    }
}