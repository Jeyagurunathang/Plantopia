package com.jey.core.data.repository

import com.jey.core.data.model.asEntity
import com.jey.core.database.dao.PlantDao
import com.jey.core.model.Plant
import javax.inject.Inject

interface PlantRepository {
    suspend fun insertPlant(plant: Plant)
}

class PlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao
) : PlantRepository {
    override suspend fun insertPlant(plant: Plant) {
        plantDao.upsertPlant(plant.asEntity())
    }
}