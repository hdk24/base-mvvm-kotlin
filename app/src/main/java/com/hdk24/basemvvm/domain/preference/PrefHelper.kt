package com.hdk24.basemvvm.domain.preference

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
interface PrefHelper {

    fun saveToString(prefName: String, prefValue: String)

    fun saveToBoolean(prefName: String, prefValue: Boolean)

    fun saveToInteger(prefName: String, prefValue: Int)

    fun readString(prefName: String, defValue: String): String?

    fun readBoolean(prefName: String, defValue: Boolean): Boolean

    fun readInteger(prefName: String, defValue: Int): Int
}
