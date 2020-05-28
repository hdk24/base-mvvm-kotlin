package com.hdk24.basemvvm.presentation.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: T)
}
