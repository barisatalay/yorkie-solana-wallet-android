package com.barisatalay.yorkiewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Flowable

@Dao
interface TokenDao : BaseDao<TokenEntity> {
    @Query("SELECT * FROM Token WHERE walletId = :walletId ORDER BY (amount * pricaUsdt) DESC")
    fun getTokenList(walletId: String): Flowable<List<TokenEntity>>

}