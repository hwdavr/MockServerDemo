package com.demo.weather.apimodel

import com.squareup.moshi.Json

data class WeatherCondition(
    @field:Json(name = "text") val description: String? = null,
    @field:Json(name = "icon") val icon: String? = null
)
