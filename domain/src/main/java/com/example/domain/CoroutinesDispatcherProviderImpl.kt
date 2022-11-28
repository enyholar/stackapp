package com.example.domain

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CoroutinesDispatcherProviderImpl @Inject constructor(): CoroutinesDispatcherProvider {

  override fun main(): CoroutineDispatcher = Dispatchers.Main

  override fun default(): CoroutineDispatcher = Dispatchers.Default

  override fun io(): CoroutineDispatcher = Dispatchers.IO
}
