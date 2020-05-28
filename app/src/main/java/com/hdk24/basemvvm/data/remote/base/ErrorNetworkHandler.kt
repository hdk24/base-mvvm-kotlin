package com.hdk24.basemvvm.data.remote.base

import com.hdk24.basemvvm.data.remote.StatusCode
import com.hdk24.basemvvm.domain.exception.Failure
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

/**
 * Handle Network Exception with Single Transformer
 * all exception will return into Failure
 */
class ErrorNetworkHandler<T> : SingleTransformer<T, T> {

    override fun apply(upstream: Single<T>): SingleSource<T> {
        return upstream.onErrorResumeNext { this.handleIfNetworkError(it) }
    }

    private fun handleIfNetworkError(throwable: Throwable): SingleSource<T> {
        if (isNetworkingError(throwable)) return asNetworkError(throwable)
        return Single.error(throwable)
    }

    private fun asNetworkError(throwable: Throwable): Single<T> {
        val failure = failureFrom(throwable)
        return Single.error(failure)
    }
}

private fun failureFrom(throwable: Throwable): Failure {
    return try {
        when {
            isConnectionTimeout(throwable) -> Failure(StatusCode.TIMEOUT, throwable.message ?: "")
            isRequestCanceled(throwable) -> Failure(
                StatusCode.REQUEST_CANCELED,
                throwable.message ?: ""
            )
            noInternetAvailable(throwable) -> Failure(
                StatusCode.NO_CONNECTION,
                throwable.message ?: ""
            )
            isHttpException(throwable) -> ErrorApiHandler.handleErrorOnNext(throwable)
            else -> Failure(StatusCode.UNKNOWN_ERROR, throwable.message ?: "")
        }
    } catch (e: Exception) {
        Failure(StatusCode.UNKNOWN_ERROR, e.message ?: "")
    }
}

private fun isNetworkingError(throwable: Throwable): Boolean {
    return isConnectionTimeout(throwable) ||
            noInternetAvailable(throwable) ||
            isRequestCanceled(throwable) ||
            isConnectError(throwable) ||
            isHttpException(throwable)
}

private fun isRequestCanceled(throwable: Throwable): Boolean {
    return throwable is IOException && throwable.message == "Canceled"
}

private fun noInternetAvailable(throwable: Throwable): Boolean {
    return throwable is UnknownHostException
}

private fun isConnectionTimeout(throwable: Throwable): Boolean {
    return throwable is SocketTimeoutException
}

private fun isConnectError(throwable: Throwable): Boolean {
    return throwable is ConnectException
}

fun isHttpException(throwable: Throwable): Boolean {
    return throwable is HttpException
}
