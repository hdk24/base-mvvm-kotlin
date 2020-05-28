package com.hdk24.basemvvm.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

data class MovieModel(
    @SerializedName("id")
    @Expose
    val id: Long? = 0L,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int? = 0,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double? = 0.0,

    @SerializedName("title")
    @Expose
    val title: String? = "",

    @SerializedName("popularity")
    @Expose
    val popularity: Double? = 0.0,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = "",

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String? = "",

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String? = "",

    @SerializedName("overview")
    @Expose
    val overview: String? = ""
)
