package com.hdk24.basemvvm.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hdk24.basemvvm.databinding.ViewMovieItemBinding
import com.hdk24.basemvvm.presentation.base.BaseViewHolder
import com.hdk24.basemvvm.presentation.model.Movie

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
class MovieAdapter : ListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback) {

    companion object {
        val MovieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(getItem(position))
    }

    class ViewHolder(private val binding: ViewMovieItemBinding) :
        BaseViewHolder<Movie>(binding.root) {

        companion object {
            fun create(parent: ViewGroup): BaseViewHolder<Movie> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ViewMovieItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        override fun bind(model: Movie) {
            binding.data = model
            binding.executePendingBindings()
        }
    }
}