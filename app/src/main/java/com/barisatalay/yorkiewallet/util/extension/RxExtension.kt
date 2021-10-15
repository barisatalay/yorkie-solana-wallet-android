package com.barisatalay.yorkiewallet.util.extension

import com.barisatalay.domain.model.Resource
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import java.util.concurrent.TimeUnit

fun <T : Any> Observable<Resource<T>>.doOnSuccess(function: (Resource.Success<T>) -> Unit): Observable<Resource<T>> {
    return doOnNext {
        if (it is Resource.Success) {
            function.invoke(it)
        }
    }
}

fun <T : Any> Single<Resource<T>>.doOnSuccessResource(function: (Resource.Success<T>) -> Unit): Single<Resource<T>> {
    return doOnSuccess {
        if (it is Resource.Success) {
            function.invoke(it)
        }
    }
}

fun <T : Any> Observable<Resource<T>>.doOnErrorResource(function: (Resource.Error) -> Unit): Observable<Resource<T>> {
    return doOnNext {
        if (it is Resource.Error) {
            function.invoke(it)
        }
    }
}

fun <T : Any> Single<Resource<T>>.doOnErrorResource(function: (Resource.Error) -> Unit): Single<Resource<T>> {
    return doOnSuccess {
        if (it is Resource.Error) {
            function.invoke(it)
        }
    }
}

fun <T : Any> Flowable<Resource<T>>.doOnSuccess(function: (Resource.Success<T>) -> Unit): Flowable<Resource<T>> {
    return doOnNext {
        if (it is Resource.Success) {
            function.invoke(it)
        }
    }
}

fun <T : Any> Flowable<Resource<T>>.doOnErrorResource(function: (Resource.Error) -> Unit): Flowable<Resource<T>> {
    return doOnNext {
        if (it is Resource.Error) {
            function.invoke(it)
        }
    }
}

fun <T> Observable<T>.retryWithDelay(maxRetries: Int, retryDelayMillis: Int): Observable<T> {
    var retryCount = 0

    return retryWhen { thObservable ->
        thObservable.flatMap { throwable ->
            if (++retryCount < maxRetries) {
                Observable.timer(retryDelayMillis.toLong(), TimeUnit.MILLISECONDS)
            } else {
                Observable.error(throwable)
            }
        }
    }
}

fun <T> Observable<T>.observeAndSubscribeOn(prmObserve: Scheduler, prmSubscribe: Scheduler? = null): Observable<T> =
    this.observeOn(prmObserve).subscribeOn(prmSubscribe ?: prmObserve)

fun <T> Flowable<T>.observeAndSubscribeOn(prmObserve: Scheduler, prmSubscribe: Scheduler? = null): Flowable<T> =
    this.observeOn(prmObserve).subscribeOn(prmSubscribe ?: prmObserve)

fun <T> Single<T>.observeAndSubscribeOn(prmObserve: Scheduler, prmSubscribe: Scheduler? = null): Single<T> =
    this.observeOn(prmObserve).subscribeOn(prmSubscribe ?: prmObserve)
