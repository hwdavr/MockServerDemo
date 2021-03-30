package com.demo.weather.repository

import android.util.Log
import com.demo.weather.api.ILocalWeatherService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityWeatherRepo @Inject
constructor(private val service: ILocalWeatherService) {
    private val TAG = CityWeatherRepo::class.java.simpleName

    init {
        Log.d(TAG, "Injection discovered")
    }

    suspend fun currentWeather(query: String) =
        service.currentWeather(query)
}
