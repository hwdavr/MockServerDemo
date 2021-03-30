package com.demo.weather.api

import com.demo.weather.di.NetworkModule
import com.demo.weather.api.weatherapi.WApiSearchCityApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchCityServiceTest {
    val api = WApiSearchCityApi(
        NetworkModule().provideRetrofit(
            NetworkModule().provideOkHttpClient()
        )
    )

    @Before
    fun setUp() {
    }

    @Test
    fun queryCities() = runBlocking {
        api.queryCities("London").collect { cities ->
            assertTrue(cities.isNotEmpty())
            println(cities)
        }
    }
}
