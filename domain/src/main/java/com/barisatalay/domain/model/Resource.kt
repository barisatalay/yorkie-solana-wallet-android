package com.barisatalay.domain.model

import com.barisatalay.domain.model.exception.BaseException

sealed class Resource<out T:Any> {

    data class Success<out T:Any>(val data:T): Resource<T>()
    data class Error(val error: BaseException): Resource<Nothing>()

}