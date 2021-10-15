package com.barisatalay.yorkiewallet.di.module

import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import com.barisatalay.yorkiewallet.data.repository.TokenRepository
import com.barisatalay.yorkiewallet.usecase.GetTokenListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideAddTokenUseCase(tokenRepository: TokenRepository, mapper: EntityToUiMapper) = GetTokenListUseCase(tokenRepository, mapper)
}