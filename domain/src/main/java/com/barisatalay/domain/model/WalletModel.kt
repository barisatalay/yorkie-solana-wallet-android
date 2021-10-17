package com.barisatalay.domain.model

data class WalletModel(
        val address: String,
        val label: String,
        val timestamp: Long,
        val networkType: NetworkType,
        val totalBalance: Double,
        var selected: Boolean = false
)