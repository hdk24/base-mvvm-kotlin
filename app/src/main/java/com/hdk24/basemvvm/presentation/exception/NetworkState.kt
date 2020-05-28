package com.hdk24.basemvvm.presentation.exception

import com.hdk24.basemvp.presentation.exception.Status
import com.hdk24.basemvvm.domain.exception.Failure


/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */
data class NetworkState private constructor(
    val status: Status,
    val failure: Failure? = null
) {
    companion object {
        val LOADED =
            NetworkState(Status.SUCCESS)
        val LOADING =
            NetworkState(Status.LOADING)
        val FAILED = NetworkState(Status.FAILED)

        fun error(failure: Failure) = NetworkState(
            Status.FAILED,
            failure
        )
    }
}
