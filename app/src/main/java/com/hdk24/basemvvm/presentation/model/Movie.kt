package com.hdk24.basemvvm.presentation.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
@Parcelize
data class Movie(

    var id: Long = 0L,

    var voteCount: Int,

    var voteAverage: Double = 0.0,

    var title: String = "",

    var popularity: Double = 0.0,

    var posterPath: String = "",

    var originalLanguage: String = "",

    var backdropPath: String = "",

    var overview: String = ""

) : Parcelable
