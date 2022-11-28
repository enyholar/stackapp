package com.example.data.remote

import com.example.domain.model.SearchUserResponse
import retrofit2.Response

interface StackExchangeApiService {
    suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String,
    ): Response<SearchUserResponse>
}