package com.demo.weather.mockserver.dispatchers

import com.demo.weather.util.ResourceUtils
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockDispatcher : Dispatcher() {
    override fun dispatch(request: RecordedRequest): MockResponse {
        return when {
            request.path?.startsWith(WEATHER_API_SEARCH_URL) == true ->
                MockResponse().setResponseCode(200).setBody(
                    ResourceUtils.getJsonString("json/search_api_success.json")
                )
            request.path?.startsWith(WEATHER_API_CURRENT_WEATHER_URL) == true ->
                MockResponse().setResponseCode(200).setBody(
                    ResourceUtils.getJsonString("json/weather_api_success.json")
                )
            else ->
                MockResponse().setResponseCode(200).setBody("")
        }
    }
}