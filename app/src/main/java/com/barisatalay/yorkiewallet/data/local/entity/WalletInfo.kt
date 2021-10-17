package com.barisatalay.yorkiewallet.data.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class WalletInfo(
        @Embedded val wallet: WalletEntity,
        @Relation(
                parentColumn = "address",
                entityColumn = "walletAddress"
        )
        val tokenList: List<TokenEntity>
)