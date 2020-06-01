package com.hdk24.basemvvm.presentation.dataSource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.hdk24.basemvvm.domain.repository.MovieRepository
import com.hdk24.basemvvm.domain.utils.SchedulerProvider
import com.hdk24.basemvvm.presentation.model.Movie
import io.reactivex.disposables.CompositeDisposable

/*
 *  Created by Hanantadk on 01/06/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 01/06/20.
 */
class MovieDataFactory(
    private val repository: MovieRepository,
    private val compositeDisposable: CompositeDisposable,
    private val scheduler: SchedulerProvider
) : DataSource.Factory<Int, Movie>() {

    private lateinit var _dataSource: MovieDataSource

    val movieLiveData = MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        _dataSource = MovieDataSource(repository, compositeDisposable, scheduler)
        movieLiveData.postValue(_dataSource)
        return _dataSource
    }
}
