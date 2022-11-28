package com.example.data

import retrofit2.HttpException
import retrofit2.Response

suspend fun <T : Any> handleApi(
    execute: suspend () -> Response<T>
): NetworkCallResult<T> {
    return try {
        val response = execute()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            NetworkCallResult.Success(body)
        } else {
            NetworkCallResult.Error(code = response.code(), message = response.message())
        }
    } catch (e: HttpException) {
        NetworkCallResult.Error(code = e.code(), message = e.message())
    } catch (e: Throwable) {
        NetworkCallResult.Exception(e)
    }
}