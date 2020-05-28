package com.hdk24.basemvvm.utils

import com.hdk24.basemvvm.BuildConfig
import timber.log.Timber

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
object AppLogger {

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    fun d(s: String?, vararg objects: Any) {
        Timber.d(s, objects)
    }

    fun d(throwable: Throwable, s: String?, vararg objects: Any) {
        Timber.d(throwable, s, objects)
    }

    fun e(s: String?, vararg objects: Any) {
        Timber.e(s, objects)
    }

    fun e(throwable: Throwable, s: String?, vararg objects: Any) {
        Timber.e(throwable, s, objects)
    }

    fun i(s: String?, vararg objects: Any) {
        Timber.i(s, objects)
    }

    fun i(throwable: Throwable, s: String?, vararg objects: Any) {
        Timber.i(throwable, s, objects)
    }

    fun w(s: String?, vararg objects: Any) {
        Timber.w(s, objects)
    }

    fun w(throwable: Throwable, s: String?, vararg objects: Any) {
        Timber.w(throwable, s, objects)
    }
}
