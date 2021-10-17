package com.barisatalay.yorkiewallet.di.module

import com.barisatalay.yorkiewallet.data.repository.ErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandler()
    }
}