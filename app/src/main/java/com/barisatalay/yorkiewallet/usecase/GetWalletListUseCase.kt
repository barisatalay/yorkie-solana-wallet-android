package com.barisatalay.yorkiewallet.usecase

import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.data.mapper.EntityToUiMapper
import com.barisatalay.yorkiewallet.data.repository.wallet.WalletRepository
import io.reactivex.Observable
import javax.inject.Inject

class GetWalletListUseCase @Inject constructor(
        private val walletRepository: WalletRepository,
        private val mapper: EntityToUiMapper
) {
    fun get(): Observable<List<WalletModel>> {
        return walletRepository.getWalletList()
                .map {
                    val result = it.map { we ->
                        mapper.walletEntityToUi(we, 0.0)
                    }

                    if (result.size == 1)
                        result.firstOrNull()?.selected = true

                    return@map result
                }
    }
}