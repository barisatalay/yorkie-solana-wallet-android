package com.barisatalay.yorkiewallet.util.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observeNotNull(lifecycleOwner: LifecycleOwner, observer: (value: T) -> Unit) {
    this.observe(lifecycleOwner, Observer { data ->
        data?.let { observer.invoke(data) }
    })
}