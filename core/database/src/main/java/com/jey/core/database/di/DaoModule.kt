package com.jey.core.database.di

import com.jey.core.database.AppDatabase
import com.jey.core.database.dao.PlantDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    @Singleton
    fun providesPlantDao(
        plantDatabase: AppDatabase
    ): PlantDao {
        return plantDatabase.plantDao()
    }
}