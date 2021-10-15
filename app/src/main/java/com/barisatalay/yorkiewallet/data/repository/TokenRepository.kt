package com.barisatalay.yorkiewallet.data.repository

import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.remote.AccountTokenResponse
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Flowable
import io.reactivex.Single

interface TokenRepository {
    fun insertTokenToLocal(entity: TokenEntity): Single<Unit>
    fun getTokenList(walletId: String): Flowable<List<TokenEntity>>
    fun getWalletBalanceFromSolApi(walletId: String): Single<Resource<AccountTokenResponse>>
}