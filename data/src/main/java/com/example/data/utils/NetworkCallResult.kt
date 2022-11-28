package com.example.data.utils

//sealed class NetworkCallResult<T : Any> {
//    class Success<T: Any>(val data: T) : NetworkCallResult<T>()
//    class Error<T: Any>(val code: Int, val message: String?) : NetworkCallResult<T>()
//    class Exception<T: Any>(val e: Throwable) : NetworkCallResult<T>()
//}

sealed interface NetworkCallResult<T : Any>

class ApiSuccess<T : Any>(val data: T) : NetworkCallResult<T>
class ApiError<T : Any>(val code: Int, val message: String?) : NetworkCallResult<T>
class ApiException<T : Any>(val e: Throwable) : NetworkCallResult<T>