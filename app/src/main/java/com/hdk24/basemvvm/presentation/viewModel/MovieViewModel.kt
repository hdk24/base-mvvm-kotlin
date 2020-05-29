package com.hdk24.basemvvm.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hdk24.basemvvm.domain.repository.MovieRepository
import com.hdk24.basemvvm.domain.utils.SchedulerProvider
import com.hdk24.basemvvm.presentation.base.BaseViewModel
import com.hdk24.basemvvm.presentation.common.ResultState
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

    private val _movie = MutableLiveData<ResultState<List<Movie>>>()

    val movie: LiveData<ResultState<List<Movie>>> get() = _movie

    internal fun fetchMovie(page: Int) {
        _movie.postValue(ResultState.OnLoading())
        lastDisposable = repository.fetchMovie(page)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe({
                _movie.postValue(ResultState.OnSuccess(it))
            }, {
                _movie.postValue(ResultState.OnError(handleNetworkError(it)))
            })

        lastDisposable?.let { compositeDisposable.add(it) }
    }
}