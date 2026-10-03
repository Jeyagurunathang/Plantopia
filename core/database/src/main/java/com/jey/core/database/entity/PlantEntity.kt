package com.jey.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.jey.core.model.Plant

@Entity(
    tableName = "plant",
    indices = [Index(value = ["plant_name"])]
)
data class PlantEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "plant_name") val plantName: String,
    val description: String,
    @ColumnInfo(name = "plant_image_path") val plantImagePath: String?,
)

fun PlantEntity.asExternalModel() = Plant(
    id = this.id,
    name = this.plantName,
    description = this.description,
    image = this.plantImagePath
)