package com.example.stackapp.di

import com.example.domain.CoroutinesDispatcherProvider
import com.example.domain.CoroutinesDispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoroutinesDispatcherModule {

  @Binds
  abstract fun bindCoroutineDispatcher(coroutinesDispatcherProviderImpl: CoroutinesDispatcherProviderImpl): CoroutinesDispatcherProvider
}
