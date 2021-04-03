package com.demo.weather.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.weather.api.weatherapi.WApiLocalWeatherApi
import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.apimodel.WeatherCondition
import com.nhaarman.mockitokotlin2.any
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CityWeatherRepoTest {
    private val service = mock(WApiLocalWeatherApi::class.java)
    private val repo = CityWeatherRepo(service)

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
    fun currentWeather() = runBlocking {
        val mockCity = "Singapore"
        val mockTime = "00:00"
        val mockTemp = "0"
        val mockIconUrl = "https://"
        val mockWeather = "Cloudy"
        val mockHumidity = "80"
        val currentCondition = CurrentCondition().apply {
            last_updated = mockTime
            temp_c = mockTemp
            humidity = mockHumidity
            condition = WeatherCondition(
                icon = mockIconUrl,
                description = mockWeather
            )
        }
        Mockito.lenient().`when`(service.currentWeather(any())).thenReturn(
            flowOf(currentCondition)
        )

        repo.currentWeather(mockCity).collect {
            assertEquals(it, currentCondition)
        }
    }
}
