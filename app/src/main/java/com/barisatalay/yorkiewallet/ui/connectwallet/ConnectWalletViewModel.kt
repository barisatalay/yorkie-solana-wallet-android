package com.barisatalay.yorkiewallet.ui.connectwallet

import androidx.lifecycle.MutableLiveData
import com.barisatalay.yorkiewallet.ui.base.BaseViewModel
import com.barisatalay.yorkiewallet.usecase.AddWalletUseCase
import com.barisatalay.yorkiewallet.util.extension.observeAndSubscribeOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ConnectWalletViewModel @Inject constructor(
    private val addWalletUseCase: AddWalletUseCase
): BaseViewModel() {
    val closeLD = MutableLiveData<Boolean>()
    fun addWallet(label: String, publicAddress: String, network: String) {
        addWalletUseCase.add(label,publicAddress, network)
                .observeAndSubscribeOn(Schedulers.io())
                .subscribe {it->
                    closeLD.postValue(true)
                }.addTo(compositeDisposable)
    }
}