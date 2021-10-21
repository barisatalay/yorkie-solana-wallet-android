package com.barisatalay.yorkiewallet.data.repository.feed

import com.barisatalay.domain.model.Resource
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Flowable
import io.reactivex.Single

interface BaseFeedRepository {
    fun insertTokenToLocal(entity: TokenEntity): Single<Unit>
    fun getWalletBalance(walletAddress: String): Flowable<Resource<Any>>
}