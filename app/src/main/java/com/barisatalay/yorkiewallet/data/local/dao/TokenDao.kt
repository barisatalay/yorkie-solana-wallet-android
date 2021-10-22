package com.barisatalay.yorkiewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import io.reactivex.Single

@Dao
interface TokenDao : BaseDao<TokenEntity> {
    @Query(" Select * from Token Where walletAddress=:walletAddress ")
    fun getTokenList(walletAddress: String): Single<List<TokenEntity>>

    @Query(" Delete From Token Where walletAddress=:walletAddress and code<>'SOL' ")
    fun removeTokens(walletAddress: String)

    @Transaction
    fun insertBeforeRemove(walletAddress: String, newList: List<TokenEntity>) {
        removeTokens(walletAddress)
        insert(newList)
    }

}