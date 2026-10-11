package com.jey.core.data.repository

import com.jey.core.database.dao.PlantDao
import com.jey.core.database.entity.PlantEntity
import com.jey.core.database.entity.asExternalModel
import com.jey.core.model.Plant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface HomeRepository {
    suspend fun getAllPlants(): Flow<List<Plant>>
}

class HomeRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao
) : HomeRepository {
    override suspend fun getAllPlants(): Flow<List<Plant>> {
        return plantDao.getAllPlants().map { it.map(PlantEntity::asExternalModel) }
    }
}