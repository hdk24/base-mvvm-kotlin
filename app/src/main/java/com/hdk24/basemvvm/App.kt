package com.hdk24.basemvvm

import android.content.Context
import androidx.multidex.MultiDex
import com.hdk24.basemvvm.di.component.AppComponent
import com.hdk24.basemvvm.di.component.DaggerAppComponent
import com.hdk24.basemvvm.utils.AppLogger
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject


/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
class App : DaggerApplication(){

    private val applicationInjector = DaggerAppComponent.builder().application(this).build()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        AppLogger.init()
    }

    override fun applicationInjector()= applicationInjector
}