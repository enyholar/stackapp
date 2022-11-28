package com.example.data

import com.example.data.remote.StackExchangeApiService
import com.example.data.repository.StackExchangeRepositoryImpl
import com.example.testdata.builder.SearchResponseBuilder
import com.example.domain.model.SearchUserResponse
import kotlin.Exception
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
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito
import org.mockito.exceptions.base.MockitoException
import retrofit2.Response


@ExperimentalCoroutinesApi
class StackExchangeRepositoryImplTest {
    private val stackExchangeApiService: StackExchangeApiService = mock()
    private val stackExchangeRepository =  StackExchangeRepositoryImpl(
       stackExchangeApiService = stackExchangeApiService
    )

    val dispatcher = StandardTestDispatcher()
    private val searchResponse = SearchResponseBuilder().build()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `when received result, then search response is returned`() =
        runTest {
            val expectedResponse: Response<SearchUserResponse> = Response.success(searchResponse)

            given(
                stackExchangeApiService.searchForUser(
                    page = any(),
                    query = any(),
                    sort = any(),
                    orderBy = any()
                )
            ).willReturn(expectedResponse)

            val result = stackExchangeRepository.searchForUser(
                page = "stackoverflow",
                query = "Gideon",
                sort = "reputation",
                orderBy = "desc"
            )

            assertThat(result).isEqualTo(searchResponse)
        }

    @Test
    fun `when received error response, then propagate error`()  = runTest{
        Mockito.`when`(
            stackExchangeApiService.searchForUser(
                page = any(),
                query = any(),
                sort = any(),
                orderBy = any()
            )
        ).thenThrow(MockitoException("something went wrong"))

        assertThrows<Exception> {
            stackExchangeRepository.searchForUser(
                page = "stackoverflow",
                query = "Gideon",
                sort = "reputation",
                orderBy = "desc"
            )
        }
    }

}