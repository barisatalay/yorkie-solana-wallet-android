package com.barisatalay.yorkiewallet.di.module

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

//    @Provides
//    @Singleton
//    fun provideAddTokenUseCase(walletRepository: WalletRepository, mapper: EntityToUiMapper) = GetTokenListUseCase(walletRepository, mapper)
}