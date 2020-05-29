package com.hdk24.basemvvm.ui.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


/*
 *  Created by Hanantadk on 28/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 28/05/20.
 */
class GridSpaceDecoration(private val spanCount: Int, private val spacing: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        outRect.left = spacing - column * spacing / spanCount
        outRect.right = (column + 1) * spacing / spanCount
        if (position < spanCount) outRect.top = spacing
        outRect.bottom = spacing

        /* without edges
        outRect.left = column * spacing / spanCount
        outRect.right = spacing - (column + 1) * spacing / spanCount
        if (position >= spanCount) outRect.top = spacing
        */
    }
}