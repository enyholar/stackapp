package com.example.testdata.coroutine

import com.example.domain.CoroutinesDispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
class TestDispatcherProviderFactory {

    fun create(dispatcher: TestDispatcher = UnconfinedTestDispatcher()): CoroutinesDispatcherProvider {
        return object : CoroutinesDispatcherProvider {
            override fun default(): CoroutineDispatcher = dispatcher
            override fun io(): CoroutineDispatcher = dispatcher
            override fun main(): CoroutineDispatcher = dispatcher
        }
    }
}
