package com.yoesuv.composenetworking.networks

sealed class NetworkResult<T> {
    data class Success<T>(val data: T) : NetworkResult<T>()
    data class HttpError<T>(val code: Int, val message: String) : NetworkResult<T>()
    data class ParseError<T>(val error: String) : NetworkResult<T>()
    data class NetworkError<T>(val error: String) : NetworkResult<T>()
    data class GenericError<T>(val error: Exception) : NetworkResult<T>()
}