package com.demo.weather.apimodel

import com.squareup.moshi.Json

data class CurrentCondition(
    @field:Json(name = "last_updated") var last_updated: String? = null,
    @field:Json(name = "temp_c") var temp_c: String? = null,
    @field:Json(name = "current") var humidity: String? = null,
    @field:Json(name = "condition") var condition: WeatherCondition? = null
)
