package com.barisatalay.yorkiewallet.data.repository.wallet

import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletInfo
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface WalletRepository {
    fun getWalletInfo(walletId: String): Flowable<WalletInfo>
    fun getWalletList(): Observable<List<WalletEntity>>
    fun addWallet(wallet: WalletEntity): Single<Unit>
    fun getActiveWallet(): Observable<WalletEntity>
    fun updateActiveWallet(address: String): Single<Unit>
    fun deleteWallet(address: String): Single<Unit>
}