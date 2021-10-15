package com.barisatalay.yorkiewallet.data.local.entity

import androidx.room.Entity

@Entity(tableName = "Token", primaryKeys = ["walletId", "address"])
data class TokenEntity(
    val walletId: String,
    val address: String,
    val code: String,
    val name: String,
    var icon: String,
    val amount: Double,
    val pricaUsdt: Double,
    val lamports: Int,
    val decimals: Double,
)