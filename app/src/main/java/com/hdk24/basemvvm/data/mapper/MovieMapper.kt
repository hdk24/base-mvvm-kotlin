package com.hdk24.basemvvm.data.mapper

import com.hdk24.basemvvm.data.local.entity.MovieEntity
import com.hdk24.basemvvm.data.remote.model.MovieModel
import com.hdk24.basemvvm.presentation.model.Movie

/*
 *  Created by Hanantadk on 21/05/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 21/05/20.
 */
object MovieMapper {

    fun transformTo(source: List<MovieModel>): List<Movie> {
        return source.map {
            Movie(
                id = it.id ?: 0,
                voteCount = it.voteCount ?: 0,
                voteAverage = it.voteAverage ?: 0.0,
                title = it.title ?: "",
                popularity = it.popularity ?: 0.0,
                posterPath = it.posterPath ?: "",
                originalLanguage = it.originalLanguage ?: "",
                backdropPath = it.backdropPath ?: "",
                overview = it.overview ?: ""
            )
        }
    }

    fun mapFromEntity(entity: List<MovieEntity>): List<Movie> {
        return entity.map {
            Movie(
                id = it.id,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                title = it.title,
                popularity = it.popularity,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                overview = it.overview
            )
        }
    }

    fun mapToEntity(model:List<Movie>):List<MovieEntity>{
        return model.map {
            MovieEntity(
                id = it.id,
                voteCount = it.voteCount,
                voteAverage = it.voteAverage,
                title = it.title,
                popularity = it.popularity,
                posterPath = it.posterPath,
                originalLanguage = it.originalLanguage,
                backdropPath = it.backdropPath,
                overview = it.overview
            )
        }
    }
}