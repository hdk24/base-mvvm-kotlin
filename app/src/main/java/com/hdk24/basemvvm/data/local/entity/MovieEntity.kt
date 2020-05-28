package com.hdk24.basemvvm.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/*
 *  Created by Hanantadk on 22/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/05/20.
 */

@Entity(tableName = "MovieTable")
data class MovieEntity(

    @PrimaryKey
    val id: Long = 0L,

    val voteCount: Int,

    val voteAverage: Double = 0.0,

    val title: String = "",

    val popularity: Double = 0.0,

    val posterPath: String = "",

    val originalLanguage: String = "",

    val backdropPath: String = "",

    val overview: String = ""
)
