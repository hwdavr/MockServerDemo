package com.demo.weather.di

import com.demo.weather.api.ILocalWeatherService
import com.demo.weather.api.ISearchCityService
import com.demo.weather.api.UrlInterceptor
import com.demo.weather.api.weatherapi.WApiLocalWeatherApi
import com.demo.weather.api.weatherapi.WApiSearchCityApi
import com.demo.weather.mockserver.MockServerManager
import com.demo.weather.util.FlowCallAdapterFactory
import com.demo.weather.util.WEATHER_API_BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Suppress("unused")
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMockServerManager() = MockServerManager()

    @Provides
    fun provideUrlInterceptor(mockServerManager: MockServerManager): UrlInterceptor {
        return UrlInterceptor(mockServerManager)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(urlInterceptor: UrlInterceptor): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(urlInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(WEATHER_API_BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .build()

    @Provides
    @Singleton
    fun provideSearchApi(
        retrofit: Retrofit
    ): ISearchCityService = WApiSearchCityApi(retrofit)

    @Provides
    @Singleton
    fun provideCityWeatherApi(
        retrofit: Retrofit
    ): ILocalWeatherService = WApiLocalWeatherApi(retrofit)
}
