package com.jey.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.jey.core.database.entity.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {
    @Query("SELECT * FROM plant")
    fun getAllPlants() : Flow<List<PlantEntity>>

    @Query("SELECT * FROM plant WHERE id = :id")
    fun getPlantById(id: Int): Flow<PlantEntity?>

    @Query("SELECT * FROM plant WHERE plant_name like :plantName")
    fun getPlantByName(plantName: String): Flow<List<PlantEntity?>>

    @Upsert
    suspend fun upsertPlant(plant: PlantEntity)

    @Delete
    suspend fun deletePlant(plant: PlantEntity)

    // TODO:
    // This DAO query is added to check whether a run-time exception is coming (or) not
    // by using synchronous DAO Query.
    // After checking this DAO query will be removed
    // NOTE: It is one-shot read method, as per the document, it has to be SUSPEND function
    @Query("SELECT * FROM plant")
    fun getAllPlantsSynchronously(): List<PlantEntity>
}