package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import com.barisatalay.yorkiewallet.data.repository.token.TokenRepository
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class GetActiveWalletUseCase @Inject constructor(
        private val walletRepository: WalletRepository,
        private val mapper: EntityToUiMapper,
        private val tokenRepository: TokenRepository
) {
    fun get(): Observable<WalletModel> {
        return walletRepository.getActiveWallet()
                .flatMap { we ->
                    tokenRepository.getTokenList(we.address)
                            .map { list ->
                                var totalBalance = 0.0
                                list.forEach { totalBalance += it.amount * it.priceUsdt }
                                mapper.walletEntityToUi(we, totalBalance)
                            }.toObservable()
                }
    }
}