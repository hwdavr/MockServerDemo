package com.demo.weather.api.weatherapi

import com.demo.weather.model.City
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface WApiSearchCityService {

    @GET(WEATHER_API_SEARCH_URL)
    fun queryCities(
        @Query("key") key: String,
        @Query("q") query: String
    ): Flow<List<City>>
}
