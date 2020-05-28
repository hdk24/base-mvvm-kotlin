package com.hdk24.basemvvm.data.preference

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.hdk24.basemvvm.di.scope.ApplicationScope
import com.hdk24.basemvvm.domain.preference.PrefHelper
import com.hdk24.basemvvm.utils.AppConstants.PREF_FILE_NAME
import javax.inject.Inject

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
@ApplicationScope
class PrefHelperImpl @Inject constructor(ctx: Application) : PrefHelper {

    private val mPrefs: SharedPreferences =
        ctx.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)

    private val editor = mPrefs.edit()

    override fun saveToString(prefName: String, prefValue: String) {
        editor.putString(prefName, prefValue)
        editor.apply()
    }

    override fun saveToBoolean(prefName: String, prefValue: Boolean) {
        editor.putBoolean(prefName, prefValue)
        editor.apply()
    }

    override fun saveToInteger(prefName: String, prefValue: Int) {
        editor.putInt(prefName, prefValue)
        editor.apply()
    }

    override fun readString(prefName: String, defValue: String): String? =
        mPrefs.getString(prefName, defValue)

    override fun readBoolean(prefName: String, defValue: Boolean): Boolean =
        mPrefs.getBoolean(prefName, defValue)

    override fun readInteger(prefName: String, defValue: Int): Int =
        mPrefs.getInt(prefName, defValue)
}
