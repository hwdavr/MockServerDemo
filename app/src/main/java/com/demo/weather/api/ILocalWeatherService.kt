package com.demo.weather.api

import com.demo.weather.apimodel.CurrentCondition
import kotlinx.coroutines.flow.Flow

interface ILocalWeatherService {
    suspend fun currentWeather(query: String): Flow<CurrentCondition?>
}
