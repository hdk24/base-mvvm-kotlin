package com.hdk24.basemvvm.presentation

import com.hdk24.basemvvm.domain.utils.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
class AppSchedulerProvider : SchedulerProvider {

    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }

    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
