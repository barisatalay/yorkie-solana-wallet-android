package com.barisatalay.yorkiewallet.data.repository.feed.solana.solscan

import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.remote.solana.SolAccountTokenResponse
import com.barisatalay.domain.model.remote.solana.SolAccountResponse
import com.barisatalay.yorkiewallet.data.local.dao.TokenDao
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.mapper.ApiToEntityMapper
import com.barisatalay.yorkiewallet.data.remote.RetrofitApi
import com.barisatalay.yorkiewallet.data.repository.BaseRepository
import com.barisatalay.yorkiewallet.util.extension.doOnSuccessResource
import io.reactivex.Flowable
import io.reactivex.Single

class SolScanRepositoryImpl constructor(
        private val tokenDao: TokenDao,
        private val api: RetrofitApi,
        private val mapper: ApiToEntityMapper
) : SolScanRepository, BaseRepository() {
    override fun insertTokenToLocal(entity: TokenEntity): Single<Unit> {
        return Single.fromCallable {
            tokenDao.insert(entity)
        }
    }

    override fun getWalletBalance(walletAddress: String): Flowable<Resource<Any>> {
        return Single.concat(getWalletBalanceAPI(walletAddress), getSolTokenBalance(walletAddress))
    }

    private fun getSolTokenBalance(walletAddress: String): Single<Resource<SolAccountResponse>> {
        return api.getSolanaTokenBalance(walletAddress).sendRequest()
                .doOnSuccessResource { resource ->
                    resource.data.data?.let {
                        val entity = mapper.solAccountToEntity(walletAddress, it)
                        tokenDao.insert(entity)
                    }
                }
    }

    private fun getWalletBalanceAPI(walletAddress: String): Single<Resource<SolAccountTokenResponse>> {
        return api.getSolanaWalletBalance(walletAddress, "1").sendRequest()
                .doOnSuccessResource { resource ->
                    resource.data.data
                            ?.filter { (it.tokenAmount?.uiAmount ?: 0.0) > 0.0 }
                            ?.map { mapper.solAccountTokenToEntity(walletAddress, it) }
                            ?.let { tokenDao.insert(it) }
                }.map { it }
    }
}