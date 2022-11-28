package com.example.stackapp

import android.util.Log
import com.example.data.remote.StackExchangeApiService
import com.example.testdata.builder.SearchResponseBuilder
import com.example.domain.usecase.SearchUserUseCase
import com.example.stackapp.presentation.userList.UserSearchViewModel
import com.example.stackapp.utils.AppDispatchers
import com.example.testdata.coroutine.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class UserSearchViewModelTest {
    private val searchUserUseCase: SearchUserUseCase = mock()
    private val log: Log = mock()

    private val searchResponse = SearchResponseBuilder.searchResponse().build()
    private val dispatcherProvider = CoroutineTestRule().testDispatcherProvider

    val dispatcher = UnconfinedTestDispatcher()
    val scope = TestScope(dispatcher)

    private val testDispatcher = AppDispatchers(
        IO = UnconfinedTestDispatcher()
    )

    private val stackExchangeApiService: StackExchangeApiService = mock()

    private val userSearchViewModel = UserSearchViewModel(
        searchUserUseCase = searchUserUseCase,
        dispatcherProvider = dispatcherProvider
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher.IO)
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
    fun `when user search, then exception is returned`() =
        runTest {
            Mockito.`when`(
                searchUserUseCase.invoke(
                    page = "stackoverflow",
                    query = "Gideon",
                    sort = "reputation",
                    orderBy = "desc"
                )
            ).thenThrow(RuntimeException())

            assertThrows<RuntimeException> {
                userSearchViewModel.getData("Gideon")
            }
        }
}