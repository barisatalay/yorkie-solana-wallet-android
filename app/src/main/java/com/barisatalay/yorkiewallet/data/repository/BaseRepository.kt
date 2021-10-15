package com.barisatalay.yorkiewallet.data.repository

import com.barisatalay.domain.model.Resource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class BaseRepository {
    @Inject
    protected lateinit var errorHandler: ErrorHandler

    protected fun <T : Any> Single<T>.sendRequest(): Single<Resource<T>> {
        return this
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<Resource<T>> {
                Resource.Success(it)
            }.onErrorReturn { error: Throwable ->
                val handledError = errorHandler.getError(error)
                return@onErrorReturn Resource.Error(handledError)
            }
    }

    protected fun <T : Any> Observable<T>.sendRequest(): Observable<Resource<T>> {
        return this
            .observeOn(Schedulers.io())
            .subscribeOn(Schedulers.io())
            .map<Resource<T>> {
                Resource.Success(it)
            }.onErrorReturn { error: Throwable ->
                val handledError = errorHandler.getError(error)
                return@onErrorReturn Resource.Error(handledError)
            }
    }
}