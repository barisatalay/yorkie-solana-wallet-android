package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.Resource
import com.barisatalay.domain.model.TokenModel
import com.barisatalay.domain.model.remote.AccountTokenResponse
import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import com.barisatalay.yorkiewallet.data.repository.TokenRepository
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class GetTokenListUseCase @Inject constructor(
    private val tokenRepository: TokenRepository,
    private val mapper: EntityToUiMapper
) {
    fun listen(walletId: String): Flowable<List<TokenModel>> {
        return tokenRepository.getTokenList(walletId)
            .map { list ->
                list.map {
                    mapper.tokenEntityToUi(it)
                }
            }
    }

    fun get(walletId: String): Single<Resource<AccountTokenResponse>> {
        return tokenRepository.getWalletBalanceFromSolApi(walletId)
    }
}