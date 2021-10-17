package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.NetworkType
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Single
import javax.inject.Inject

class AddWalletUseCase @Inject constructor(
    private val walletRepository: WalletRepository
) {
    fun add(label: String, publicAddress: String, network: String): Single<Unit> {
        return walletRepository.addWallet(createEntity(label, publicAddress, network))
    }

    private fun createEntity(label: String, publicAddress: String, network: String): WalletEntity {
        return WalletEntity(
            label = label,
            address = publicAddress,
            networkType = NetworkType.find(network.lowercase()),
            timestamp = System.currentTimeMillis()
        )
    }
}