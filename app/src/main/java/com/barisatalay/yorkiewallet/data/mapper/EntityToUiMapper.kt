package com.barisatalay.yorkiewallet.data.mapper

import com.barisatalay.domain.model.TokenModel
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity

class EntityToUiMapper {
    fun tokenEntityToUi(it: TokenEntity): TokenModel {
        return TokenModel(
            code = it.code,
            name = it.name,
            amount = it.amount,
            priceUSDT = it.pricaUsdt,
            icon = it.icon,
            calculated = it.amount * it.pricaUsdt,
        )
    }
}