package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.NetworkType
import com.barisatalay.domain.model.Resource
import com.barisatalay.yorkiewallet.data.repository.feed.solana.rpc.SolRPCRepository
import com.barisatalay.yorkiewallet.data.repository.feed.solana.solscan.SolScanRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetWalletBalanceRemoteUseCase @Inject constructor(
        private val solRPCRepository: SolRPCRepository,
        private val solScanRepository: SolScanRepository,
) {
    fun get(walletId: String, networkType: NetworkType, isPermissionless: Boolean): Flowable<Resource<Any>> {
        //for Solana RPC
        return when (networkType) {
            NetworkType.Unknown -> TODO()
            NetworkType.SOLANA -> {
                if (isPermissionless)
                    solScanRepository.getWalletBalance(walletId)
                else
                    solRPCRepository.getWalletBalance(walletId)
            }
        }
    }
}