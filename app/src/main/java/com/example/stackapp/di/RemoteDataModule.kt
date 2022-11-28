package com.example.stackapp.di

import com.example.data.api.StackExchangeApi
import com.example.data.remote.StackExchangeApiService
import com.example.data.remote.StackExchangeServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideStackExchangeRemoteDataSource(stackExchangeApi: StackExchangeApi) : StackExchangeApiService =
        StackExchangeServiceImpl(stackExchangeApi = stackExchangeApi)
}