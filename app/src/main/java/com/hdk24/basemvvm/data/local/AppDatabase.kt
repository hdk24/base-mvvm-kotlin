package com.hdk24.basemvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hdk24.basemvvm.data.local.AppDatabase.Companion.DB_VERSION
import com.hdk24.basemvvm.data.local.dao.MovieDao
import com.hdk24.basemvvm.data.local.entity.MovieEntity

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
@Database(entities = [MovieEntity::class], version = DB_VERSION, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "basemvvm.db"
        const val DB_VERSION = 1
    }

    abstract fun movieDao(): MovieDao
}
