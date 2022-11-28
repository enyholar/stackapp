package com.example.data.remote

import com.example.domain.model.SearchUserResponse

interface StackExchangeApiService {
    suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String,
    ): SearchUserResponse
}