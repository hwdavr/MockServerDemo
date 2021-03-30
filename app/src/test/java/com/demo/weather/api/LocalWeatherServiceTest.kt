package com.demo.weather.api

import com.demo.weather.di.NetworkModule
import com.demo.weather.api.weatherapi.WApiLocalWeatherApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertFalse
import org.junit.Test

class LocalWeatherServiceTest {
    val api = WApiLocalWeatherApi(
        NetworkModule().provideRetrofit(
            NetworkModule().provideOkHttpClient()
        )
    )

    @Test
    fun currentWeather() = runBlocking {
        api.currentWeather("London").collect { weather ->
            assertFalse(weather?.condition?.icon.isNullOrEmpty())
            assertFalse(weather?.condition?.description.isNullOrEmpty())
            assertFalse(weather?.temp_c.isNullOrEmpty())
            assertFalse(weather?.last_updated.isNullOrEmpty())
            println(weather)
        }
    }
}
