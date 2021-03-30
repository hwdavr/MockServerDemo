package com.demo.weather.repository

import android.util.Log
import com.demo.weather.api.ISearchCityService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QueryCityRepo @Inject
constructor(private val service: ISearchCityService) {
    private val TAG = QueryCityRepo::class.java.simpleName

    init {
        Log.d(TAG, "Injection discovered")
    }

    suspend fun queryCities(query: String) =
        service.queryCities(query)

}
