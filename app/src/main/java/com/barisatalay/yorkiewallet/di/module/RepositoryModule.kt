package com.barisatalay.yorkiewallet.di.module

import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.dao.WalletDao
import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.data.repository.token.TokenRepository
import com.barisatalay.yorkiewallet.data.repository.token.TokenRepositoryImpl
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWalletRepository(walletDao: WalletDao): WalletRepository = WalletRepositoryImpl(walletDao)

    @Provides
    @Singleton
    fun provideTokenRepository(tokenDao: TokenDao, api: RetrofitApi, mapper: ApiToEntityMapper): TokenRepository = TokenRepositoryImpl(tokenDao, api, mapper)

}