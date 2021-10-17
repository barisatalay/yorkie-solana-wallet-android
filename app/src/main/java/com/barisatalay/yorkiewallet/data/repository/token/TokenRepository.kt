package com.barisatalay.yorkiewallet.data.repository.token

import com.barisatalay.domain.model.NetworkType
import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.remote.AccountTokenResponse
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Observable
import io.reactivex.Single

interface TokenRepository {
    fun insertTokenToLocal(entity: TokenEntity): Single<Unit>
    fun getWalletBalanceFromApi(walletAddress: String, networkType: NetworkType): Single<Resource<Any>>
    fun getTokenList(walletAddress: String): Single<List<TokenEntity>>
}