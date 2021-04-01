package com.demo.weather.di

import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.mock.mockserver.MockServerManager
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
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
