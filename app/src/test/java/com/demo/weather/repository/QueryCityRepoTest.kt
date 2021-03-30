package com.demo.weather.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.weather.api.weatherapi.WApiSearchCityApi
import com.demo.weather.model.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.lenient
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class QueryCityRepoTest {
    val service = mock(WApiSearchCityApi::class.java)
    var repo = QueryCityRepo(service)

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun queryCities() = runBlockingTest {
        lenient().`when`(service.queryCities(com.nhaarman.mockitokotlin2.any())).thenReturn(
            flowOf(
                listOf(
                    City("City 1", 0),
                    City("City 2", 2)
                )
            )
        )

        repo.queryCities("test 1").collect { list ->
            assertFalse(list.isNullOrEmpty())
            assertEquals(list.size, 2)
            print("Get list size ${list.size}")
        }
        delay(1_000)
    }
}
