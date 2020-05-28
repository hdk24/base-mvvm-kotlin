package com.hdk24.basemvvm.utils

import android.os.Build
import com.hdk24.basemvvm.BuildConfig

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
object AppConstants {

    val USER_AGENT =
        "android / " + Build.VERSION.SDK_INT + " / " +
                Build.VERSION.RELEASE + " / " +
                BuildConfig.VERSION_NAME + " / " +
                Build.MODEL + " / " +
                Build.MANUFACTURER + " / " +
                Build.BRAND

    const val PREF_FILE_NAME = "com.hdk24.basemvp.PREF_FILE_KEY"
}
