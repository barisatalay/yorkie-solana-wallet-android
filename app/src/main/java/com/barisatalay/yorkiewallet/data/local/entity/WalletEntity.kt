package com.barisatalay.yorkiewallet.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barisatalay.domain.model.NetworkType

@Entity(tableName = "Wallet")
data class WalletEntity(
        @PrimaryKey
        var address: String,
        var label: String,
        var timestamp: Long,
        var networkType: NetworkType,
        var isActive: Boolean = false
)