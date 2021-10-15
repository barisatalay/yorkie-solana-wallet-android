package com.barisatalay.yorkiewallet.ui.home

import androidx.lifecycle.MutableLiveData
import com.barisatalay.domain.model.TokenModel
import com.barisatalay.yorkiewallet.ui.base.BaseViewModel
import com.barisatalay.yorkiewallet.usecase.GetTokenListUseCase
import com.barisatalay.yorkiewallet.util.extension.observeAndSubscribeOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTokenListUseCase: GetTokenListUseCase
) : BaseViewModel() {
    val walletIconLD = MutableLiveData<String>()
    val walletTokenList = MutableLiveData<List<TokenModel>>()

    fun getTokenList(walletId: String) {
        walletIconLD.postValue("https://cdn.jsdelivr.net/gh/trustwallet/assets@master/blockchains/solana/info/logo.png")

        getTokenListUseCase.get(walletId)
            .observeAndSubscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun listenTokenList(walletId: String) {
        getTokenListUseCase.listen(walletId)
            .observeAndSubscribeOn(Schedulers.io())
            .subscribe {
                walletTokenList.postValue(it)
            }.addTo(compositeDisposable)
    }
}