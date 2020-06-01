package com.hdk24.basemvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.hdk24.basemvvm.domain.repository.MovieRepository
import com.hdk24.basemvvm.domain.utils.SchedulerProvider
import com.hdk24.basemvvm.presentation.base.BaseViewModel
import com.hdk24.basemvvm.presentation.common.ResultState
import com.hdk24.basemvvm.presentation.dataSource.MovieDataFactory
import com.hdk24.basemvvm.presentation.dataSource.MovieDataSource
import com.hdk24.basemvvm.presentation.model.Movie
import javax.inject.Inject

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val schedulers: SchedulerProvider
) : BaseViewModel() {

    private val _count = 20

    lateinit var movieList:  LiveData<PagedList<Movie>>

    private val _movieFactory = MovieDataFactory(repository, compositeDisposable, schedulers)

    private val config = PagedList.Config.Builder()
        .setPageSize(_count)
        .setInitialLoadSizeHint(_count * 2)
        .setEnablePlaceholders(false)
        .build()

    fun fetchMovie() {
        movieList = LivePagedListBuilder(_movieFactory, config).build()
    }

    fun getState(): LiveData<ResultState<List<Movie>>> = Transformations.switchMap<MovieDataSource,
            ResultState<List<Movie>>>(_movieFactory.movieLiveData, MovieDataSource::state)

    fun listIsEmpty(): Boolean = movieList.value?.isEmpty() ?: true

    fun retry() {
        _movieFactory.movieLiveData.value?.retry()
    }

    fun refresh() {
        _movieFactory.movieLiveData.value?.invalidate()
    }
}
