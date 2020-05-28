package com.hdk24.basemvvm.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.hdk24.basemvvm.data.local.AppDatabase
import com.hdk24.basemvvm.data.local.dao.MovieDao
import com.hdk24.basemvvm.di.scope.ApplicationScope
import dagger.Module
import dagger.Provides

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
@Module
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration() // db will cleared when upgrade version
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideMovieDao(database: AppDatabase): MovieDao {
        return database.movieDao()
    }
}