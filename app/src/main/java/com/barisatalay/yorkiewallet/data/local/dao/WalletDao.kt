package com.barisatalay.yorkiewallet.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.data.local.entity.TokenEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletInfo
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
interface WalletDao : BaseDao<WalletEntity> {
    @Transaction
    @Query(" Select W.*, T.* " +
            " From Wallet as W " +
            " Left Join Token as T on T.walletAddress = W.address  " +
            " Where W.address=:walletId " +
            " ORDER BY (T.amount * T.priceUsdt) DESC ")
    fun getWalletAndTokenList(walletId: String): Flowable<WalletInfo>

    @Query("Select * From Wallet Order By timestamp ")
    fun getWalletList(): Observable<List<WalletEntity>>

    @Transaction
    @Query(" Update Wallet Set isActive = 1 Where 1 = (Select Count(1) From Wallet) ")
    fun updateActiveWalletIfNotExist()

    @Query(" Select * From Wallet Where isActive = 1  Order By timestamp LIMIT 1")
    fun getActiveWallet(): Observable<WalletEntity>

    @Transaction
    @Query(" Delete from Wallet Where address=:address ")
    fun deleteWallet(address: String)

    @Query(" Update Wallet Set isActive = 0 ")
    fun clearActiveWallet()

    @Query(" Update Wallet Set isActive = 1  Where address=:address and isActive=0")
    fun setActiveWallet(address: String)

    @Transaction
    fun updateActiveWallet(address: String) {
        clearActiveWallet()
        setActiveWallet(address)
    }
}