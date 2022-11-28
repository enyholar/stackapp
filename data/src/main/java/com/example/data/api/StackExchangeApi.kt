package com.example.data.api

import com.example.domain.model.SearchUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StackExchangeApi {
    @GET("users")
    suspend fun searchForUser(
        @Query("site") page: String,
        @Query("inname") query: String,
        @Query("sort") sort: String,
        @Query("order") orderBy: String,
    ): Response<SearchUserResponse>
}