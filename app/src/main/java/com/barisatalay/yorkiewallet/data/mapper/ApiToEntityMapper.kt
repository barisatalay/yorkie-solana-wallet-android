package com.barisatalay.yorkiewallet.data.mapper

import com.barisatalay.domain.model.remote.solana.SolAccountTokenResult
import com.barisatalay.domain.model.remote.solana.SolAccountResult
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity

class ApiToEntityMapper {
    fun solAccountTokenToEntity(walletAddress: String, it: SolAccountTokenResult): TokenEntity {
        return TokenEntity(
                walletAddress = walletAddress,
                contractAddress = it.tokenAddress.orEmpty(),
                code = it.tokenSymbol.orEmpty(),
                name = it.tokenName.orEmpty(),
                icon = it.tokenIcon.orEmpty(),
                amount = it.tokenAmount?.uiAmount ?: 0.0,
                priceUsdt = it.priceUsdt ?: 0.0,
                lamports = it.lamports ?: 0,
                decimals = it.tokenAmount?.decimals ?: 0.0,
                isMainToken = false
        )
    }

    fun solAccountToEntity(walletAddress: String, responseResult: SolAccountResult, marketPrice: Double?): TokenEntity {
        return TokenEntity(
                walletAddress = walletAddress,
                contractAddress = responseResult.ownerProgram.orEmpty(),
                code = "SOL",
                name = "Solana",
                icon = "https://cdn.jsdelivr.net/gh/trustwallet/assets@master/blockchains/solana/info/logo.png",
                amount = (responseResult.lamports ?: 0) / (1000000000.0),
                priceUsdt = marketPrice ?: 0.0,
                lamports = responseResult.lamports ?: 0,
                decimals = 6.0,
                isMainToken = true
        )
    }
}