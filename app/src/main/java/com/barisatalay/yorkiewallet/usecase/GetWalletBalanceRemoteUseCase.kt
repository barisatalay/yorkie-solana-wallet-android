package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.NetworkType
import com.barisatalay.domain.model.Resource
import com.barisatalay.yorkiewallet.data.repository.token.TokenRepository
import io.reactivex.Single
import javax.inject.Inject

class GetWalletBalanceRemoteUseCase @Inject constructor(
        private val tokenRepository: TokenRepository
) {
    fun get(walletId: String, networkType: NetworkType): Single<Resource<Any>> {
        return tokenRepository.getWalletBalanceFromApi(walletId, networkType)

    }
}