package com.barisatalay.domain.model

data class WalletAndTokenModel(
        val wallet: WalletModel,
        val tokenList: List<TokenModel>
)