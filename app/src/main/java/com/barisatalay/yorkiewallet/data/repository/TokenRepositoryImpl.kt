package com.barisatalay.yorkiewallet.data.repository

import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.remote.AccountTokenResponse
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.util.extension.doOnSuccessResource
import io.reactivex.Flowable
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

    override fun getTokenList(walletId: String): Flowable<List<TokenEntity>> {
        return tokenDao.getTokenList(walletId)
    }

    override fun getWalletBalanceFromSolApi(walletId: String): Single<Resource<AccountTokenResponse>> {
        return api.getWalletBalance(walletId, "1").sendRequest()
            .doOnSuccessResource { resource ->
                resource.data.data
                    ?.filter { (it.tokenAmount?.uiAmount ?: 0.0) > 0.0 }
                    ?.forEach {
                        tokenDao.insert(mapper.accountTokenToEntity(walletId, it))
                    }
            }
    }


}