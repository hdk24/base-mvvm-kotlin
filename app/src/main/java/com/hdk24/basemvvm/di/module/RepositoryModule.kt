package com.hdk24.basemvvm.di.module

import com.hdk24.basemvvm.data.local.AppDatabase
import com.hdk24.basemvvm.data.remote.api.MovieAPI
import com.hdk24.basemvvm.data.repository.MovieRepositoryImpl
import com.hdk24.basemvvm.di.scope.ApplicationScope
import com.hdk24.basemvvm.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
@Module
class RepositoryModule {

    @Provides
    @ApplicationScope
    fun provideMovieRepository(service: MovieAPI, database: AppDatabase): MovieRepository {
        return MovieRepositoryImpl(service, database)
    }
}
