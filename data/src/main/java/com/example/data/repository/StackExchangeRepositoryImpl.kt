package com.example.data.repository

import com.example.data.remote.StackExchangeApiService
import com.example.domain.repository.StackExchangeRepository
import com.example.stackapp.model.User
import retrofit2.Response
import com.example.domain.utils.Result
class StackExchangeRepositoryImpl(
    private val stackExchangeApiService: StackExchangeApiService
) : StackExchangeRepository {

    override suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String
    ) = stackExchangeApiService.searchForUser(
        page = page,
        query = query,
        sort = sort,
        orderBy = orderBy
    )
}