package com.hdk24.basemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hdk24.basemvvm.databinding.ViewMovieItemBinding
import com.hdk24.basemvvm.presentation.base.BaseViewHolder
import com.hdk24.basemvvm.presentation.model.Movie

/*
 *  Created by Hanantadk on 28/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/05/20.
 */
class MovieHolder(private val binding: ViewMovieItemBinding) :
    BaseViewHolder<Movie>(binding.root) {

    companion object {
        fun create(parent: ViewGroup): BaseViewHolder<Movie> {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewMovieItemBinding.inflate(layoutInflater, parent, false)
            return MovieHolder(binding)
        }
    }

    override fun bind(model: Movie) {
        binding.data = model
        binding.executePendingBindings()
    }
}