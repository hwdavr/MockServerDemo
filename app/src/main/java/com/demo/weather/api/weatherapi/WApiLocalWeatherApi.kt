package com.demo.weather.api.weatherapi

import com.demo.weather.api.ILocalWeatherService
import com.demo.weather.apimodel.CurrentCondition
import com.demo.weather.util.WEATHER_API_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Retrofit
import javax.inject.Inject

class WApiLocalWeatherApi  @Inject constructor(
    retrofit: Retrofit
) : ILocalWeatherService {
    private val TAG = WApiLocalWeatherApi::class.java.simpleName
    private val apiService = retrofit.create(WApiLocalWeatherService::class.java)

    override suspend fun currentWeather(query: String): Flow<CurrentCondition?> =
        apiService.currentWeather(WEATHER_API_KEY, query).map { it.current }
}
