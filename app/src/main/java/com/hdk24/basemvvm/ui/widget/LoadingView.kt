package com.hdk24.basemvvm.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import com.hdk24.basemvvm.R
import com.hdk24.basemvvm.data.remote.StatusCode
import com.hdk24.basemvvm.presentation.common.LoadingState
import kotlinx.android.synthetic.main.view_loading_item.view.*

/*
 *  Created by Hanantadk on 28/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/05/20.
 */
class LoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    var onRefreshListener: (() -> Unit)? = null

    @LayoutRes
    private var loadingLayoutRes: Int = R.layout.shimmer_movie_loading

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.LoadingView, 0, 0)
            .apply {
                try {
                    loadingLayoutRes = getResourceId(
                        R.styleable.LoadingView_layout,
                        R.layout.shimmer_movie_loading
                    )

                } finally {
                    recycle()
                }
            }

        LayoutInflater.from(context).inflate(R.layout.view_loading_item, this, true)
        View.inflate(context, loadingLayoutRes, shimmer_container)
        btn_retry.setOnClickListener { onRefreshListener?.invoke() }
    }

    fun setState(loadingState: LoadingState? = LoadingState.OnLoading) {
        when (loadingState) {
            is LoadingState.OnLoading -> {
                this.visibility = View.VISIBLE
                shimmer_container.visibility = View.VISIBLE
                container_error.visibility = View.GONE
            }

            is LoadingState.OnFinish -> {
                this.visibility = View.GONE
            }

            is LoadingState.OnError -> {
                this.visibility = View.VISIBLE
                shimmer_container.visibility = View.GONE
                handleErrorState(loadingState.code)
            }
        }
    }

    private fun handleErrorState(state: Int) {
        container_error.visibility = View.VISIBLE
        when (state) {
            StatusCode.NO_CONTENT -> {
                title_error.text = context.getString(R.string.message_empty_movie)
                subtitle_error.text = context.getString(R.string.desc_empty_movie)
                //image_error.setImageResource(R.drawable.ic_illustration_not_found)
                btn_retry.visibility = View.GONE
            }

            StatusCode.NO_CONNECTION -> {
                title_error.text = context.getString(R.string.message_no_connection)
                subtitle_error.text = context.getString(R.string.desc_no_connection)
                //image_error.setImageResource(R.drawable.ic_illustration_no_connection)
                btn_retry.text = context.getString(R.string.action_try_again)
            }

            StatusCode.UNKNOWN_ERROR -> {
                title_error.text = context.getString(R.string.label_oops)
                subtitle_error.text = context.getString(R.string.desc_unknown_error)
                //image_error.setImageResource(R.drawable.ic_illustration_no_connection)
            }
        }
    }
}
