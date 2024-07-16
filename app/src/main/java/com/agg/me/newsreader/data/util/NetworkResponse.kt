package com.agg.me.newsreader.data.util

sealed class NetworkResponse<T>(
    val code: Int = 0,
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResponse<T>(data = data)
    class Loading<T>(data: T? = null) : NetworkResponse<T>(data = data)
    class Error<T>(code: Int = 0, message: String?, data: T? = null) :
        NetworkResponse<T>(code, data, message)
}