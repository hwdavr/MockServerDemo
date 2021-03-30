package com.demo.weather.apimodel

import com.squareup.moshi.Json

data class LocalWeather(
    @field:Json(name = "current") val current: CurrentCondition? = null
)
