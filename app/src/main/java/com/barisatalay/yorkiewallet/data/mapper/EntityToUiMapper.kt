package com.barisatalay.yorkiewallet.data.mapper

import com.barisatalay.domain.model.TokenModel
import com.barisatalay.domain.model.WalletAndTokenModel
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletInfo

class EntityToUiMapper {
    fun walletInfoToUi(walletInfo: WalletInfo): WalletAndTokenModel {
        val list = walletInfo.tokenList.map {
            TokenModel(
                    code = it.code,
                    name = it.name,
                    amount = it.amount,
                    priceUSDT = it.priceUsdt,
                    icon = it.icon,
                    calculated = it.amount * it.priceUsdt,
            )
        }.sortedByDescending {
            it.calculated
        }
        var totalBalance = 0.0
        list.forEach { totalBalance += it.calculated }

        return WalletAndTokenModel(
                wallet = walletEntityToUi(walletInfo.wallet, totalBalance),
                tokenList = list
        )
    }

    fun walletEntityToUi(entity: WalletEntity, totalBalance: Double): WalletModel {
        return WalletModel(
                address = entity.address,
                label = entity.label,
                timestamp = entity.timestamp,
                networkType = entity.networkType,
                totalBalance = totalBalance,
                selected = false
        )
    }
}