package com.demo.weather.api

import com.demo.weather.model.City
import kotlinx.coroutines.flow.Flow

interface ISearchCityService {
    suspend fun queryCities(query: String): Flow<List<City>>
}
