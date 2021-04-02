package com.demo.weather.api

import com.demo.weather.BaseMockServerTest
import com.demo.weather.di.NetworkModule
import com.demo.weather.api.weatherapi.WApiSearchCityApi
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchCityServiceTest : BaseMockServerTest() {
    private val api = WApiSearchCityApi(retrofit)

    @Test
    fun queryCities() = runBlocking {
        mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
        api.queryCities("London").collect { cities ->
            assertEquals(5, cities.size)
        }
    }
}
