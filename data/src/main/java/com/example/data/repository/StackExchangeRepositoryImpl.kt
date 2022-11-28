package com.example.data.repository

import com.example.data.remote.StackExchangeApiService
import com.example.data.utils.ApiError
import com.example.data.utils.ApiException
import com.example.data.utils.ApiSuccess
import com.example.data.utils.handleApi
import com.example.domain.model.SearchUserResponse
import com.example.domain.repository.StackExchangeRepository

class StackExchangeRepositoryImpl(
    private val stackExchangeApiService: StackExchangeApiService
) : StackExchangeRepository {

    override suspend fun searchForUser(
        page: String,
        query: String,
        sort: String,
        orderBy: String
    ): SearchUserResponse {
        val networkCallResult = handleApi {
            stackExchangeApiService.searchForUser(
                page = page,
                query = query,
                sort = sort,
                orderBy = orderBy
            )
        }
        return when (networkCallResult) {
            is ApiSuccess -> networkCallResult.data
            is ApiError -> throw Throwable(networkCallResult.message)
            is ApiException -> throw networkCallResult.e
        }
    }
}