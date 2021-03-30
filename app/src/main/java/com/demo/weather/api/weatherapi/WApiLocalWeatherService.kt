package com.demo.weather.api.weatherapi

import com.demo.weather.apimodel.LocalWeather
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WApiLocalWeatherService {

    @GET(WEATHER_API_CURRENT_WEATHER_URL)
    fun currentWeather(
        @Query("key") key: String,
        @Query("q") query: String
    ): Flow<LocalWeather>
}
