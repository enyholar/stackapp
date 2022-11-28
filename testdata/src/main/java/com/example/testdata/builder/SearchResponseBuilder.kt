package com.example.testdata.builder

import com.example.domain.model.SearchUserResponse
import com.example.stackapp.model.User

class SearchResponseBuilder {

    private val user: User = User(displayName = DEFAULT_DISPLAY_NAME)
    private val userList = listOf(user,user)
    fun build () = SearchUserResponse(
        quotaMax = 10,
        quotaRemaining = 100,
        items = userList,
        hasMore = true
    )
    companion object {

        private const val DEFAULT_DISPLAY_NAME = "Gideon"
        fun searchResponse() = SearchResponseBuilder()
    }
}