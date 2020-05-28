package com.hdk24.basemvvm.data.remote

/*
 *  Created by Hanantadk on 22/03/20.
 *  Copyright (c) 2020. All rights reserved.
 *  Last modified 22/03/20.
 */

/**
 * 1xx Informational – the request was received, continuing process
 * 2xx Successful – the request was successfully received, understood and accepted
 * 3xx Redirection – further action needs to be taken in order to complete the request
 * 4xx Client Error – the request contains bad syntax or cannot be fulfilled
 * 5xx Server Error – the server failed to fulfill an apparently valid request
 */
object StatusCode {

    // Informational status code : 1xx
    // add informational code here

    // Success Code
    const val SUCCESS = 200
    const val NO_CONTENT = 204

    // Client Error Code
    const val REQUEST_CANCELED = 417

    // Server Error Code
    const val SERVER_ERROR = 500
    const val NO_CONNECTION = 503
    const val TIMEOUT = 504
    const val UNKNOWN_ERROR = 520
}
