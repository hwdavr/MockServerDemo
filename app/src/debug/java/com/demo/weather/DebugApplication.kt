package com.demo.weather

import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.api.UrlInterceptorHolder
import com.demo.weather.di.DaggerDebugAppComponent
import com.demo.weather.mock.mockserver.MockServerManager
import dagger.android.DaggerApplication
import okhttp3.OkHttpClient
import javax.inject.Inject

class DebugApplication : DaggerApplication() {
    private val appComponent = DaggerDebugAppComponent.builder()
        .application(this)
        .build()

    @Inject
    lateinit var mockServerManager: MockServerManager

    @Inject
    lateinit var urlInterceptorHolder: UrlInterceptorHolder

    // Get for instrumentation test
    @Inject
    lateinit var okHttpClient: OkHttpClient

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        (urlInterceptorHolder.urlInterceptor as? DebugUrlInterceptor)
            ?.mockServerManager = mockServerManager
    }

    override fun applicationInjector() = appComponent
}