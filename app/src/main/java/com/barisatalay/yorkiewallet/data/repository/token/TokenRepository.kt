package com.barisatalay.yorkiewallet.data.repository.token

import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Single

interface TokenRepository {
    fun getTokenList(walletAddress: String): Single<List<TokenEntity>>
}