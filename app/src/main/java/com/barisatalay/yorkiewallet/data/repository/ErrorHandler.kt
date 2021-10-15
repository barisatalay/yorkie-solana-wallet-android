package com.barisatalay.yorkiewallet.data.repository

import android.content.Context
import com.barisatalay.domain.model.exception.BaseException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLException
import javax.net.ssl.SSLHandshakeException

class ErrorHandler @Inject constructor(){

    fun getError(throwable: Throwable): BaseException {
        return when (throwable) {
            is MalformedJsonException, is JsonParseException, is JsonSyntaxException -> BaseException.JsonException(throwable)
            is UnknownHostException -> BaseException.UnknownHostException(throwable)
            is TimeoutException -> BaseException.TimeoutException(throwable)
            is SSLHandshakeException -> BaseException.SSLHandshakeException(throwable)
            is SocketTimeoutException -> BaseException.SocketTimeoutException(throwable)
            is ConnectException -> BaseException.ConnectException(throwable)
            is SSLException -> BaseException.SSLException(throwable)
            is IOException -> BaseException.UnknownException(throwable)
            else -> BaseException.UnknownException(throwable)
        }
    }
}