package com.example.domain.usecase

import com.example.domain.repository.StackExchangeRepository

class SearchUserUseCase(
    private val stackExchangeRepository: StackExchangeRepository
) {
    suspend fun invoke(
        page: String,
        query: String,
        sort: String,
        orderBy: String,
    ) = stackExchangeRepository.searchForUser(
        page = page,
        sort = sort,
        orderBy = orderBy,
        query = query
    )
}