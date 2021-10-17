package com.barisatalay.yorkiewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Single

@Dao
interface TokenDao : BaseDao<TokenEntity> {
    @Query(" Select * from Token Where walletAddress=:walletAddress ")
    fun getTokenList(walletAddress: String): Single<List<TokenEntity>>
}