package com.example.stackapp.di
import com.example.data.remote.StackExchangeApiService
import com.example.data.repository.StackExchangeRepositoryImpl
import com.example.domain.repository.StackExchangeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideStackExchangeRepository(
        stackExchangeApiService: StackExchangeApiService,
    ): StackExchangeRepository =
        StackExchangeRepositoryImpl(stackExchangeApiService = stackExchangeApiService)
}