package com.demo.weather.mockserver.dispatchers

import com.demo.weather.mockserver.MockScenarios
import com.demo.weather.util.ResourceUtils
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class SearchCityApiDispatcher(
    private val mockApis: Map<String, MockScenarios>
) : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when(mockApis[WEATHER_API_SEARCH_URL]) {
            MockScenarios.SUCCESS ->
                MockResponse().setResponseCode(200).setBody(
                    ResourceUtils.getJsonString("json/search_api_success.json")
                )
            MockScenarios.FAILURE ->
                MockResponse().setResponseCode(400)
            else ->
                MockResponse().setResponseCode(404)
        }
    }
}