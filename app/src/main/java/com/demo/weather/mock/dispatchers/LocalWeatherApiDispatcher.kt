package com.demo.weather.mock.dispatchers

import com.demo.weather.mock.MockUtils
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.ResourceUtils
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class LocalWeatherApiDispatcher(
    private val mockApis: Map<String, MockScenarios>
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(mockApis[WEATHER_API_CURRENT_WEATHER_URL]) {
            MockScenarios.SUCCESS ->
                MockUtils.success("weather_api_success.json")
            MockScenarios.FAILURE ->
                MockUtils.failure(500)
            else ->
                MockUtils.default
        }
    }
}