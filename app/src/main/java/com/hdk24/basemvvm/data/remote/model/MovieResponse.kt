package com.hdk24.basemvvm.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

data class MovieResponse(

    @SerializedName("page")
    @Expose
    val page: Int = 0,

    @SerializedName("total_results")
    @Expose
    val totalResults: Int = 0,

    @SerializedName("total_pages")
    @Expose
    val totalPages: Int = 0,

    @SerializedName("results")
    @Expose
    val results: List<MovieModel> = emptyList()
)
