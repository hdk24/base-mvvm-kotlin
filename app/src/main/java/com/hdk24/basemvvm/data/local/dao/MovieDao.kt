package com.hdk24.basemvvm.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.hdk24.basemvvm.data.local.entity.MovieEntity
import io.reactivex.Single

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */
@Dao
interface MovieDao : BaseDao<MovieEntity>{

    @Query("SELECT * FROM MovieTable")
    fun getAll(): Single<List<MovieEntity>>

    @Query("SELECT * FROM MovieTable WHERE title LIKE '%' || :keyword || '%' ORDER BY id DESC")
    fun getByKeyword(keyword: String): Single<List<MovieEntity>>

    @Query("DELETE FROM MovieTable")
    fun truncate()
}