package com.jey.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jey.core.database.dao.PlantDao
import com.jey.core.database.entity.PlantEntity

@Database(
    entities = [PlantEntity::class],
    exportSchema = true,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}