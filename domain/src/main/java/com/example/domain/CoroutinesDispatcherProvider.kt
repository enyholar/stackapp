package com.example.domain

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatcherProvider {

  fun main(): CoroutineDispatcher

  fun default(): CoroutineDispatcher

  fun io(): CoroutineDispatcher
}
