package com.barisatalay.yorkiewallet.di.module

import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.data.repository.ErrorHandler
import com.barisatalay.yorkiewallet.data.repository.TokenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {
    @Provides
    fun provideErrorHandler(): ErrorHandler {
        return ErrorHandler()
    }

    @Provides
    @Singleton
    fun provideTokenRepositoryImpl(tokenDao: TokenDao, api: RetrofitApi, apiToEntityMapper: ApiToEntityMapper): TokenRepositoryImpl {
        return TokenRepositoryImpl(tokenDao, api, apiToEntityMapper)
    }
}