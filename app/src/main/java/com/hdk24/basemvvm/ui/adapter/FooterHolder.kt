package com.hdk24.basemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hdk24.basemvvm.databinding.ViewFooterItemBinding
import com.hdk24.basemvvm.presentation.base.BaseViewHolder
import com.hdk24.basemvvm.presentation.common.LoadingState

/*
 *  Created by Hanantadk on 28/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/05/20.
 */
class FooterHolder(private val binding: ViewFooterItemBinding) :
    BaseViewHolder<LoadingState>(binding.root) {

    companion object {
        fun create(retry: () -> Unit, parent: ViewGroup): BaseViewHolder<LoadingState> {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewFooterItemBinding.inflate(layoutInflater, parent, false)
            binding.footerError.setOnClickListener {
                binding.showLoading = true
                binding.showError = false
                retry.invoke()
            }
            return FooterHolder(binding)
        }
    }

    override fun bind(model: LoadingState) {
        binding.showLoading = model is LoadingState.OnLoading
        binding.showError = model is LoadingState.OnError
        binding.executePendingBindings()
    }
}
