package com.hdk24.basemvvm.data.repository

import com.hdk24.basemvvm.data.local.AppDatabase
import com.hdk24.basemvvm.data.mapper.MovieMapper
import com.hdk24.basemvvm.data.remote.api.MovieAPI
import com.hdk24.basemvvm.data.remote.base.ErrorNetworkHandler
import com.hdk24.basemvvm.domain.repository.MovieRepository
import com.hdk24.basemvvm.presentation.model.Movie
import io.reactivex.Single
import javax.inject.Inject

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 *
 *  https://medium.com/corebuild-software/android-repository-pattern-using-rx-room-bac6c65d7385
 */
class MovieRepositoryImpl @Inject constructor(
    private val service: MovieAPI,
    private val database: AppDatabase
) : MovieRepository {

    override fun fetchMovie(page: Int): Single<List<Movie>> {
        return service.getMovies(page = page)
            .compose(ErrorNetworkHandler())
            .map { MovieMapper.transformTo(it.results) }
    }
}
