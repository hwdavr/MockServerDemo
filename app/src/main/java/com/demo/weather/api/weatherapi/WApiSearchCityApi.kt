package com.demo.weather.api.weatherapi

import com.demo.weather.api.ISearchCityService
import com.demo.weather.util.WEATHER_API_KEY
import retrofit2.Retrofit
import javax.inject.Inject

class WApiSearchCityApi @Inject constructor(
    retrofit: Retrofit
) : ISearchCityService {
    private val apiService = retrofit.create(WApiSearchCityService::class.java)

    override suspend fun queryCities(query: String) =
        apiService.queryCities(WEATHER_API_KEY, query)

}
