package com.hdk24.basemvvm.data.local.dao

import androidx.room.*

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
@Dao
interface BaseDao<T>  {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(data: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: T)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateList(data: List<T>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(data: T)

    @Delete
    fun delete(data: T)
}