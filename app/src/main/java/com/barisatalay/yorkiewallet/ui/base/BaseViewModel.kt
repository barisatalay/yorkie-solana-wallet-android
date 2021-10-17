package com.barisatalay.yorkiewallet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {
    protected val compositeDisposable = CompositeDisposable()
    val isDataLoading = MutableLiveData<Boolean>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    protected fun openLoading(){
        isDataLoading.postValue(true)
    }

    protected fun closeLoading(){
        isDataLoading.postValue(false)
    }
}