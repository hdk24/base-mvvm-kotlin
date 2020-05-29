package com.hdk24.basemvvm.presentation.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import com.hdk24.basemvvm.data.remote.StatusCode
import com.hdk24.basemvvm.domain.exception.Failure
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
abstract class BaseViewModel : ViewModel() {

    protected var lastDisposable: Disposable? = null

    val compositeDisposable = CompositeDisposable()

    /**
     * unsubscribe last subscription rxJava
     */
    fun disposeLast() {
        lastDisposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    /**
     * clear all subscription and dispose
     */
    private fun dispose() {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    /**
     * handle error request on throwable as Failure
     * @param throwable wrapp with failure
     * @return failure throwable
     */

    fun handleNetworkError(throwable: Throwable?): Failure {
        return if (throwable is Failure) throwable
        else Failure(StatusCode.UNKNOWN_ERROR, "There is unknown error")

    }

    /**
     * dispose all subscription when lifecycle onDestory
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    override fun onCleared() {
        dispose()
        super.onCleared()
    }
}
