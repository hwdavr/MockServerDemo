package com.demo.weather.di

import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.mock.mockserver.MockServerManager
import com.demo.weather.util.LOCAL_HOST
import com.demo.weather.util.LOCAL_HOST_CERT_PIN
import com.demo.weather.util.WEATHER_API_CERT_PIN
import com.demo.weather.util.WEATHER_API_DOMAIN
import dagger.Module
import dagger.Provides
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import javax.inject.Singleton

@Suppress("unused")
@Module
class DebugNetworkModule : NetworkModule() {

    override fun getUrlInterceptor(): Interceptor {
        return DebugUrlInterceptor()
    }

    override fun getCertificatePinner() =
        CertificatePinner.Builder()
            .add(WEATHER_API_DOMAIN, WEATHER_API_CERT_PIN)
            .add(LOCAL_HOST, LOCAL_HOST_CERT_PIN)
            .build()

    @Provides
    @Singleton
    fun provideMockServerManager() = MockServerManager()
}
