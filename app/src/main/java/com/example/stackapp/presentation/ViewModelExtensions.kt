package com.example.stackapp.presentation


import com.example.stackapp.R
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun translateExceptionToStringId(throwable: Throwable?): Int {
    return when (throwable) {
        is UnknownHostException,
        is IOException -> R.string.error_no_network
        is TimeoutException,
        is SocketTimeoutException -> R.string.error_network_timeout
        is ClassCastException,
        is NullPointerException -> R.string.error_message_default
        else -> R.string.error_message_default
    }
}
