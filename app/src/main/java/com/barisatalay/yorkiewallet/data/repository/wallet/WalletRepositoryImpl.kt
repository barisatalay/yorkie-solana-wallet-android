package com.barisatalay.yorkiewallet.data.repository.wallet

import com.barisatalay.yorkiewallet.data.local.dao.WalletDao
import com.barisatalay.yorkiewallet.data.local.entity.WalletEntity
import com.barisatalay.yorkiewallet.data.local.entity.WalletInfo
import com.barisatalay.yorkiewallet.data.repository.BaseRepository
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WalletRepositoryImpl @Inject constructor(
        private val walletDao: WalletDao
) : WalletRepository, BaseRepository() {

    override fun getWalletInfo(walletId: String): Flowable<WalletInfo> {
        return walletDao.getWalletAndTokenList(walletId)
    }

    override fun getWalletList(): Observable<List<WalletEntity>> {
        return walletDao.getWalletList()
    }

    override fun addWallet(wallet: WalletEntity): Single<Unit> {
        return Single.fromCallable {
            walletDao.insertIgnore(wallet)
            walletDao.updateActiveWalletIfNotExist()
        }
    }

    override fun getActiveWallet(): Observable<WalletEntity> {
        return walletDao.getActiveWallet()
    }

    override fun updateActiveWallet(address: String): Single<Unit> {
        return Single.fromCallable {
            walletDao.updateActiveWallet(address)
        }
    }

    override fun deleteWallet(address: String): Single<Unit> {
        return Single.fromCallable { walletDao.deleteWallet(address) }
    }
}