package com.hdk24.basemvvm.extension

import android.view.View
import androidx.appcompat.app.ActionBar

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */

fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

fun ActionBar.showBackArrow(shouldShow: Boolean) {
    this.setDisplayHomeAsUpEnabled(shouldShow)
}