package com.barisatalay.yorkiewallet.data.repository.feed.solana.rpc

import com.barisatalay.domain.model.Resource
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.data.repository.BaseRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class SolRPCRepositoryImpl: SolRPCRepository, BaseRepository() {
    override fun insertTokenToLocal(entity: TokenEntity): Single<Unit> {
        TODO("Not yet implemented")
    }

    override fun getWalletBalance(walletAddress: String): Flowable<Resource<Any>> {
        TODO("Not yet implemented")
    }
}