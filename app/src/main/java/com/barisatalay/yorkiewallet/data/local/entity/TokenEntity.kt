package com.barisatalay.yorkiewallet.data.local.entity

import androidx.room.Entity

@Entity(tableName = "Token", primaryKeys = ["walletAddress", "contractAddress"])
data class TokenEntity(
        val walletAddress: String,
        val contractAddress: String,
        val code: String,
        val name: String,
        var icon: String,
        val amount: Double,
        val priceUsdt: Double,
        val lamports: Int,
        val decimals: Double,
)