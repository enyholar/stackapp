package com.example.stackapp

import com.example.data.remote.StackExchangeApiService
import com.example.data.repository.StackExchangeRepositoryImpl
import com.example.domain.model.SearchResponseBuilder
import com.example.domain.usecase.SearchUserUseCase
import com.example.stackapp.presentation.userList.UserSearchViewModel
import com.example.stackapp.utils.AppDispatchers
import com.example.testdata.coroutine.CoroutineTestRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
@ExperimentalCoroutinesApi
class UserSearchViewModelTest {
    private val searchUserUseCase: SearchUserUseCase = mock()

    private val searchResponse = SearchResponseBuilder.searchResponse().build()
    private val dispatcherProvider = CoroutineTestRule().testDispatcherProvider

//    @get:Rule
//    var coroutinesRule = CoroutinesTestRule()

    val dispatcher = UnconfinedTestDispatcher()
    val scope = TestScope(dispatcher)

    private val testDispatcher = AppDispatchers(
        IO = UnconfinedTestDispatcher()
    )

    private val stackExchangeApiService: StackExchangeApiService = mock()

    private val userSearchViewModel = UserSearchViewModel(
        searchUserUseCase = searchUserUseCase,
        dispatcherProvider = dispatcherProvider
       // appDispatchers = testDispatcher
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
    fun `when received successful response, then search response is retrieved from api`() =
        runTest {
            given(
                searchUserUseCase.invoke(
                    page = "stackoverflow",
                    query = "Gideon",
                    sort = "reputation",
                    orderBy = "desc"
                )
            ).willReturn(searchResponse)

//            given(
//                stackExchangeRepository.searchForUser(
//                    page = any(),
//                    query = any(),
//                    sort = any(),
//                    orderBy = any()
//                )
//            ).willReturn(searchResponse)

             val aa = userSearchViewModel.getData("Gideon")
            assertThat(userSearchViewModel.uiState.user).isEqualTo(searchResponse.items)
        }
}