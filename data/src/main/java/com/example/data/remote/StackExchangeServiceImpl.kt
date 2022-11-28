package com.example.data.remote

import com.example.data.api.StackExchangeApi

class StackExchangeServiceImpl(
    private val stackExchangeApi: StackExchangeApi
) : StackExchangeApiService {


    override suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String
    ) = stackExchangeApi.searchForUser(
        page = page,
        query = query,
        sort = sort,
        orderBy = orderBy
    )
}