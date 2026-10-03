package com.jey.core.data.di

import com.jey.core.data.repository.HomeRepository
import com.jey.core.data.repository.HomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsHomeRepository(
        homeRepository: HomeRepositoryImpl
    ): HomeRepository
}