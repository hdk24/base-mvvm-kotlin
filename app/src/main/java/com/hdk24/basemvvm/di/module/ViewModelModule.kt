package com.hdk24.basemvvm.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hdk24.basemvvm.presentation.ViewModelFactory
import com.hdk24.basemvvm.di.scope.ActivityScoped
import com.hdk24.basemvvm.di.scope.ApplicationScope
import com.hdk24.basemvvm.di.scope.ViewModelKey
import com.hdk24.basemvvm.presentation.viewModel.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @ApplicationScope
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory) : ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    internal abstract fun providesMovieViewModel(viewModel: MovieViewModel) : ViewModel
}