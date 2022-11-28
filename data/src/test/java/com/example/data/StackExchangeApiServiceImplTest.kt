package com.example.data

import com.example.data.api.StackExchangeApi
import com.example.data.remote.StackExchangeApiService
import com.example.data.remote.StackExchangeServiceImpl
import com.example.testdata.builder.SearchResponseBuilder
import com.example.domain.model.SearchUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.`when`
import org.mockito.exceptions.base.MockitoException
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import retrofit2.Response


@ExperimentalCoroutinesApi
class StackExchangeApiServiceImplTest {
    private val stackExchangeApi: StackExchangeApi = mock()
    private val stackExchangeApiService: StackExchangeApiService = StackExchangeServiceImpl(
        stackExchangeApi = stackExchangeApi
    )

    val dispatcher = StandardTestDispatcher()
    val scope = TestScope(dispatcher)
    private val searchResponse = SearchResponseBuilder.searchResponse().build()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when received successful response, then search response is retrieved from api`() =
        runTest {
            val expectedResponse: Response<SearchUserResponse> = Response.success(searchResponse)

            given(
                stackExchangeApi.searchForUser(
                    page = any(),
                    query = any(),
                    sort = any(),
                    orderBy = any()
                )
            ).willReturn(expectedResponse)

            val result = stackExchangeApiService.searchForUser(
                page = "stackoverflow",
                query = "Gideon",
                sort = "reputation",
                orderBy = "desc"
            )

            assertThat(result.body()).isEqualTo(searchResponse)

        }

    @Test
    fun `when received error response, then propagate error`() = runTest {
        `when`(
            stackExchangeApi.searchForUser(
                page = any(),
                query = any(),
                sort = any(),
                orderBy = any()
            )
        ).thenThrow(MockitoException("something went wrong"))
        assertThrows<MockitoException> {
            stackExchangeApiService.searchForUser(
                page = "stackoverflow",
                query = "Gideon",
                sort = "reputation",
                orderBy = "desc"
            )
        }

    }

}