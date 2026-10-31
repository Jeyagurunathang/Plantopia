package com.jey.feature.addplant.impl

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jey.core.android.utils.FileUtils
import com.jey.core.data.repository.PlantRepository
import com.jey.core.model.Plant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlantScreenViewModel @Inject constructor(
    private val plantRepository: PlantRepository,
    private val fileUtils: FileUtils
) : ViewModel() {
    fun insertDataIntoDb(
        name: String,
        description: String,
        plantImage: String?,
    ) {
        var plantImagePath = ""

        viewModelScope.launch {
            plantImage?.let { imageUri ->
                plantImagePath = fileUtils.saveToDir(imageUri.toUri(), Dispatchers.IO)
                Log.d("File_path", "Plant Image file plant: $plantImagePath")
            }

            plantRepository.insertPlant(
                Plant(
                    name = name,
                    description = description,
                    image = plantImagePath
                )
            )
        }
    }
}