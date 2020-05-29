package com.hdk24.basemvvm.utils

import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.hdk24.basemvvm.R

/*
 *  Created by Hanantadk on 28/04/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/04/20.
 */
object DisplayUtils {

    fun setTransparentStatusBar(window: Window) {
        val bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        setWindowFlag(window, bits, true)

        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION)

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(
                window,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                false
            )

            window.statusBarColor = ContextCompat.getColor(
                window.context,
                R.color.colorBlack20
            )
        }
    }

    private fun setWindowFlag(window: Window, bits: Int, on: Boolean) {
        val winParams = window.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        window.attributes = winParams
    }
}