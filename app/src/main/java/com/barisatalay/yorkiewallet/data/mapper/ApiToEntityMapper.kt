package com.barisatalay.yorkiewallet.data.mapper

import com.barisatalay.domain.model.remote.AccountTokenResult
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity

class ApiToEntityMapper {
    fun accountTokenToEntity(walletId: String, it: AccountTokenResult): TokenEntity {
        return TokenEntity(
            walletId = walletId,
            address = it.tokenAddress.orEmpty(),
            code = it.tokenSymbol.orEmpty(),
            name = it.tokenName.orEmpty(),
            icon = it.tokenIcon.orEmpty(),
            amount = it.tokenAmount?.uiAmount ?: 0.0,
            pricaUsdt = it.priceUsdt ?: 0.0,
            lamports = it.lamports ?: 0,
            decimals = it.tokenAmount?.decimals ?: 0.0
        ).apply {
            if (icon.isEmpty())
                icon = "https://sonar.watch/logos/coins/unknown.jpg"
        }
    }
}