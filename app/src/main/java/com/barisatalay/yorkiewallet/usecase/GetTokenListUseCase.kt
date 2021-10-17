package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.WalletAndTokenModel
import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetTokenListUseCase @Inject constructor(
        private val walletRepository: WalletRepository,
        private val mapper: EntityToUiMapper
) {
    fun listen(walletId: String): Flowable<WalletAndTokenModel> {
        return walletRepository.getWalletInfo(walletId)
                .map { wallet ->
                    mapper.walletInfoToUi(wallet)
                }
    }
}