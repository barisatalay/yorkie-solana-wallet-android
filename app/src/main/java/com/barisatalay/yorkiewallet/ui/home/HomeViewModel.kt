package com.barisatalay.yorkiewallet.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.barisatalay.domain.model.NetworkType
import com.barisatalay.domain.model.TokenModel
import com.barisatalay.domain.model.WalletModel
import com.barisatalay.yorkiewallet.ui.base.BaseViewModel
import com.barisatalay.yorkiewallet.usecase.GetActiveWalletUseCase
import com.barisatalay.yorkiewallet.usecase.GetWalletBalanceRemoteUseCase
import com.barisatalay.yorkiewallet.usecase.GetTokenListUseCase
import com.barisatalay.yorkiewallet.util.extension.observeAndSubscribeOn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val getTokenListUseCase: GetTokenListUseCase,
        private val getActiveWalletUseCase: GetActiveWalletUseCase,
        private val getWalletBalanceRemoteUseCase: GetWalletBalanceRemoteUseCase
) : BaseViewModel() {
    val activeWalletLD = MutableLiveData<WalletModel>()
    val walletTokenListLD = MutableLiveData<List<TokenModel>>()
    val isRemoteLoading = MutableLiveData<Boolean>()
    val isWalletLoading = MutableLiveData<Boolean>()
    val isLiquidityLoading = MutableLiveData<Boolean>()
    val isYieldFarmingLoading = MutableLiveData<Boolean>()

    fun listenActiveWallet() {
        getActiveWalletUseCase.get()
                .observeAndSubscribeOn(Schedulers.io())
                .subscribe {
                    activeWalletLD.postValue(it)
                }.addTo(compositeDisposable)
    }

    fun listenTokenList(walletId: String) {
        getTokenListUseCase.listen(walletId)
                .observeAndSubscribeOn(Schedulers.io())
                .subscribe {
                    walletTokenListLD.postValue(it.tokenList)
                }.addTo(compositeDisposable)
    }

    fun getTokenList(walletId: String, networkType: NetworkType) {
        getWalletBalanceRemoteUseCase.get(walletId, networkType, true)
                .observeAndSubscribeOn(Schedulers.io())
                .doOnSubscribe { isWalletLoading.postValue(true) }
                .subscribe { it ->
                    isWalletLoading.postValue(false)
                    isRemoteLoading.postValue(false)
                    Log.d("HomeViewModel", "getTokenList Finished")
                }.addTo(compositeDisposable)
    }

}