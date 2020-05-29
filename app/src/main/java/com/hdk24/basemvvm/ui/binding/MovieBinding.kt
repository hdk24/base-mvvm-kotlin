package com.hdk24.basemvvm.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.hdk24.basemvvm.BuildConfig
import com.hdk24.basemvvm.presentation.common.LoadingState
import com.hdk24.basemvvm.ui.widget.LoadingView

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
@BindingAdapter("imagePoster")
fun bindImage(imgView: ImageView, url: String) {
    Glide.with(imgView.context)
        .load(BuildConfig.BASE_IMAGE + url)
        .into(imgView)
}

@BindingAdapter("loadingState")
fun bindLoadingState(loadingView: LoadingView, state: LoadingState) {
   loadingView.setState(state)
}