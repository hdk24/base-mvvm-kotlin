package com.hdk24.basemvvm.data.remote.api

import com.hdk24.basemvvm.data.remote.model.MovieResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
interface MovieAPI {

    @GET("discover/movie")
    fun getMovies(
        @Query("sort_by") sortBy: String = "release_date.desc",
        @Query("page") page: Int = 1
    ): Single<MovieResponse>
}
