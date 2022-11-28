package com.example.stackapp.di

import com.example.domain.repository.StackExchangeRepository
import com.example.domain.usecase.SearchUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideStackExchangeUseCases(stackExchangeRepository: StackExchangeRepository) =
        SearchUserUseCase(stackExchangeRepository = stackExchangeRepository)
}