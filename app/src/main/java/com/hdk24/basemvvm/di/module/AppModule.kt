package com.hdk24.basemvvm.di.module

import android.app.Application
import android.content.Context
import com.hdk24.basemvvm.App
import com.hdk24.basemvvm.presentation.AppSchedulerProvider
import com.hdk24.basemvvm.data.preference.PrefHelperImpl
import com.hdk24.basemvvm.di.ApplicationContext
import com.hdk24.basemvvm.di.scope.ApplicationScope
import com.hdk24.basemvvm.domain.preference.PrefHelper
import com.hdk24.basemvvm.domain.utils.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.Provides

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
@Module
class AppModule {

    @Provides
    @ApplicationScope
    fun providePreferencesHelper(app: Application): PrefHelper = PrefHelperImpl(app)

    @Provides
    @ApplicationScope
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()
}