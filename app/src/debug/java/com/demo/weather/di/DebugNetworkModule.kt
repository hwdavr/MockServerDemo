package com.demo.weather.di

import com.demo.weather.api.ILocalWeatherService
import com.demo.weather.api.ISearchCityService
import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.api.UrlInterceptor
import com.demo.weather.api.weatherapi.WApiLocalWeatherApi
import com.demo.weather.api.weatherapi.WApiSearchCityApi
import com.demo.weather.mockserver.MockServerManager
import com.demo.weather.util.FlowCallAdapterFactory
import com.demo.weather.util.WEATHER_API_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
class DebugNetworkModule : NetworkModule() {

    override fun getUrlInterceptor(): Interceptor {
        return DebugUrlInterceptor()
    }

    @Provides
    @Singleton
    fun provideMockServerManager() = MockServerManager()
}
