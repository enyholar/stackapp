package com.example.domain.repository

import com.example.domain.model.SearchUserResponse

interface StackExchangeRepository {
    suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String,
    ): SearchUserResponse
}