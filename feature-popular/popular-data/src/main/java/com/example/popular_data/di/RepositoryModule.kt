package com.example.popular_data.di

import com.example.popular_data.repository.IPopularRepository
import com.example.popular_data.repository.PopularRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsPopularRepository(
        popularRepositoryImpl: PopularRepositoryImpl
    ): IPopularRepository
}