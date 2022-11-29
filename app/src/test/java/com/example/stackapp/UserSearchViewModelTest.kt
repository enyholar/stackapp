package com.example.stackapp

import com.example.testdata.builder.SearchResponseBuilder
import com.example.domain.usecase.SearchUserUseCase
import com.example.stackapp.presentation.userList.UserSearchViewModel
import com.example.testdata.coroutine.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class UserSearchViewModelTest {
    private val searchUserUseCase: SearchUserUseCase = mock()
    private val searchResponse = SearchResponseBuilder.searchResponse().build()
    private val dispatcherProvider = CoroutineTestRule().testDispatcherProvider

    private val userSearchViewModel = UserSearchViewModel(
        searchUserUseCase = searchUserUseCase,
        dispatcherProvider = dispatcherProvider
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcherProvider.io())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when user search, then search response is returned with list of user`() =
        runTest {
            given(
                searchUserUseCase.invoke(
                    page = "stackoverflow",
                    query = "Gideon",
                    sort = "reputation",
                    orderBy = "desc"
                )
            ).willReturn(searchResponse)

            userSearchViewModel.getData("Gideon")
            assertThat(userSearchViewModel.uiState.user).isEqualTo(searchResponse.items)
        }

    @Test
    fun `when user search, then event is SearchUserError event `() =
        runTest {
            Mockito.`when`(
                searchUserUseCase.invoke(
                    page = "stackoverflow",
                    query = "Gideon",
                    sort = "reputation",
                    orderBy = "desc"
                )
            ).thenThrow(RuntimeException())
            userSearchViewModel.getData("Gideon")
            val event = userSearchViewModel.uiState.events.firstOrNull()
            assertThat(event).isInstanceOf(UserSearchViewModel.UserSearchEvent.SearchUserError::class.java)
        }
}