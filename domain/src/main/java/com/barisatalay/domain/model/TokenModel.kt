package com.barisatalay.domain.model

data class TokenModel(
    val code: String,
    val name: String,
    val amount: Double,
    val priceUSDT: Double,
    val icon: String,
    val calculated: Double
)