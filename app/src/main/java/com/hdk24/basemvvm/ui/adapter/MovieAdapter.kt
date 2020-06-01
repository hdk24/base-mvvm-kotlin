package com.hdk24.basemvvm.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hdk24.basemvvm.presentation.common.LoadingState
import com.hdk24.basemvvm.presentation.model.Movie

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
class MovieAdapter(private val retry: () -> Unit) :
    PagedListAdapter<Movie, RecyclerView.ViewHolder>(MovieDiffCallback) {

    companion object {

        const val VIEW_TYPE_ITEM = 0

        const val VIEW_TYPE_FOOTER = 1

        val MovieDiffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    private var state: LoadingState = LoadingState.OnFinish

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_FOOTER -> FooterHolder.create(retry, parent)
            else -> MovieHolder.create(parent)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MovieHolder) {
            holder.bind(getItem(position)!!)
        } else if (holder is FooterHolder) {
            holder.bind(state)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasFooter() && position == itemCount - 1) VIEW_TYPE_FOOTER else VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) 1 else 0
    }

    private fun hasFooter(): Boolean {
        return state is LoadingState.OnLoading || state is LoadingState.OnError
    }

    fun setState(state: LoadingState) {
        this.state = state
        notifyItemChanged(itemCount)
    }
}
