package com.barisatalay.yorkiewallet.ui.main

import androidx.lifecycle.MutableLiveData
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.ui.base.BaseViewModel
import com.barisatalay.yorkiewallet.usecase.DeleteWalletUseCase
import com.barisatalay.yorkiewallet.usecase.GetWalletListUseCase
import com.barisatalay.yorkiewallet.usecase.UpdateActiveWalletUseCase
import com.barisatalay.yorkiewallet.util.extension.observeAndSubscribeOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val getWalletListUseCase: GetWalletListUseCase,
        private val updateActiveWalletUseCase: UpdateActiveWalletUseCase,
        private val deleteWalletUseCase: DeleteWalletUseCase
) : BaseViewModel() {
    val walletInfoLD = MutableLiveData<List<WalletModel>>()

    fun getWalletList() {
        getWalletListUseCase.get()
                .observeAndSubscribeOn(Schedulers.io())
                .subscribe {
                    walletInfoLD.postValue(it)
                }.addTo(compositeDisposable)
    }

    fun updateActiveWallet(address: String) {
        updateActiveWalletUseCase.update(address)
                .observeAndSubscribeOn(Schedulers.io())
                .doOnSubscribe { openLoading() }
                .subscribe { it -> closeLoading() }
                .addTo(compositeDisposable)
    }

    fun deleteWallet(address: String) {
        deleteWalletUseCase.delete(address)
                .observeAndSubscribeOn(Schedulers.io())
                .doOnSubscribe { openLoading() }
                .subscribe { it -> closeLoading() }
                .addTo(compositeDisposable)
    }

}