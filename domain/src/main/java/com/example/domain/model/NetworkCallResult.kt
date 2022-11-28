package com.example.domain.model

sealed class NetworkCallResult<T : Any> {
    class Success<T: Any>(val data: T) : NetworkCallResult<T>()
    class Error<T: Any>(val code: Int, val message: String?) : NetworkCallResult<T>()
    class Exception<T: Any>(val e: Throwable) : NetworkCallResult<T>()
}