package com.demo.weather.api

import com.demo.weather.BaseMockServerTest
import com.demo.weather.di.NetworkModule
import com.demo.weather.api.weatherapi.WApiLocalWeatherApi
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class LocalWeatherServiceTest : BaseMockServerTest() {
    private val api = WApiLocalWeatherApi(retrofit)

    @Test
    fun currentWeather() = runBlocking {
        mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.SUCCESS)
        api.currentWeather("London").collect { weather ->
            assertEquals("//cdn.weatherapi.com/weather/64x64/night/116.png", weather?.condition?.icon)
            assertEquals("Partly cloudy", weather?.condition?.description)
            assertEquals("26.0",  weather?.temp_c)
            assertEquals("2021-03-30 23:15", weather?.last_updated)
        }
    }
}
