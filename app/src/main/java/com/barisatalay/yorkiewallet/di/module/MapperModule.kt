package com.barisatalay.yorkiewallet.di.module

import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {
    @Provides
    @Singleton
    fun provideApiToEntityMapper() = ApiToEntityMapper()

    @Provides
    @Singleton
    fun provideEntityToUiMapper() = EntityToUiMapper()
}