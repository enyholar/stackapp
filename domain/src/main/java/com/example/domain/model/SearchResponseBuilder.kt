package com.example.domain.model

import com.example.stackapp.model.User

class SearchResponseBuilder {

    private val user: User = User(displayName = "Gideon")
    private val userList = listOf(user,user)
    fun build () = SearchUserResponse(
        quotaMax = 10,
        quotaRemaining = 100,
        items = userList,
        hasMore = true
    )
    companion object {

        private const val DEFAULT_DISPLAY_NAME = "Gideon"
        private const val URL = "url"

        fun searchResponse() = SearchResponseBuilder()
    }
}