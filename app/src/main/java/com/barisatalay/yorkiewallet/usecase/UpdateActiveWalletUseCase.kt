package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Single
import javax.inject.Inject

class UpdateActiveWalletUseCase @Inject constructor(
        private val walletRepository: WalletRepository
) {
    fun update(address: String): Single<Unit> {
        return walletRepository.updateActiveWallet(address)
    }
}