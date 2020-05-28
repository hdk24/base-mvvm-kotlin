package com.hdk24.basemvvm.presentation.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.hdk24.basemvvm.domain.preference.PrefHelper
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
abstract class BaseActivity<B : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var prefHelper:PrefHelper

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mViewDataBinding: B

    val binding: B get() = mViewDataBinding

    @LayoutRes
    protected abstract fun getLayoutId(): Int

    protected abstract fun onViewReady(savedInstance: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        super.onCreate(savedInstanceState)
        if (getLayoutId() != 0) {
            mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
            onViewReady(savedInstanceState)
        }
    }
}