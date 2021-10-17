package com.barisatalay.yorkiewallet.data.repository.token

import com.barisatalay.domain.model.NetworkType
import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.remote.AccountTokenResponse
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.data.repository.BaseRepository
import com.barisatalay.yorkiewallet.util.extension.doOnSuccessResource
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
        private val tokenDao: TokenDao,
        private val api: RetrofitApi,
        private val mapper: ApiToEntityMapper
) : TokenRepository, BaseRepository() {
    override fun insertTokenToLocal(entity: TokenEntity): Single<Unit> {
        return Single.fromCallable {
            tokenDao.insert(entity)
        }
    }

    override fun getWalletBalanceFromApi(walletAddress: String, networkType: NetworkType): Single<Resource<Any>> {
        return when (networkType) {
            NetworkType.Unknown -> TODO()
            NetworkType.SOLANA -> getWalletBalanceFromSolApi(walletAddress)
        }
    }

    override fun getTokenList(walletAddress: String): Single<List<TokenEntity>> {
        return tokenDao.getTokenList(walletAddress)
    }

    private fun getWalletBalanceFromSolApi(walletAddress: String): Single<Resource<Any>> {
        return api.getWalletBalance(walletAddress, "1").sendRequest()
                .doOnSuccessResource { resource ->
                    resource.data.data
                            ?.filter { (it.tokenAmount?.uiAmount ?: 0.0) > 0.0 }
                            ?.map { mapper.accountTokenToEntity(walletAddress, it) }
                            ?.let { tokenDao.insert(it) }
                }.map { it }

    }
}