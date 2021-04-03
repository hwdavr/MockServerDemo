package com.demo.weather.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.apimodel.WeatherCondition
import com.demo.weather.repository.CityWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CityWeatherViewModelTest {
    private lateinit var repository: CityWeatherRepo
    private lateinit var viewModel: CityWeatherViewModel
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        Dispatchers.setMain(mainThreadSurrogate)
        repository = Mockito.mock(CityWeatherRepo::class.java)
        viewModel = CityWeatherViewModel(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun checkCurrentWeather() = runBlocking {
        val mockCity = "Singapore"
        val mockTime = "00:00"
        val mockTemp = "0"
        val mockIconUrl = "icon.png"
        val mockWeather = "Cloudy"
        val mockHumidity = "80"
        Mockito.lenient().`when`(repository.currentWeather(anyString())).thenReturn(
            flowOf(
                CurrentCondition().apply {
                    last_updated = mockTime
                    temp_c = mockTemp
                    humidity = mockHumidity
                    condition = WeatherCondition(
                        icon = mockIconUrl,
                        description = mockWeather
                    )
                }
            )
        )

        viewModel.checkCurrentWeather(mockCity)

        delay(1_000)
        assertEquals(viewModel.updatedTimeLiveData.value, "Last updated: $mockTime")
        assertEquals(viewModel.temperatureLiveData.value, mockTemp)
        assertEquals(viewModel.weatherIconUrlLiveData.value, "https:$mockIconUrl")
        assertEquals(viewModel.weatherDescLiveData.value, mockWeather)
        assertEquals(viewModel.humidityLiveData.value, "Humidity: $mockHumidity")
    }
}
