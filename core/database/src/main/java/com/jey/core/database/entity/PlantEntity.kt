package com.jey.core.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

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
