package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Single
import javax.inject.Inject

class DeleteWalletUseCase @Inject constructor(
        private val walletRepository: WalletRepository
) {
    fun delete(address: String): Single<Unit> {
        return walletRepository.deleteWallet(address)
    }
}