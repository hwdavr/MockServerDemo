package com.demo.weather

import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.api.UrlInterceptorHolder
import com.demo.weather.di.DaggerDebugAppComponent
import com.demo.weather.mockserver.MockServerManager
import dagger.android.DaggerApplication
import javax.inject.Inject

class DebugApplication : DaggerApplication() {
    private val appComponent = DaggerDebugAppComponent.builder()
        .application(this)
        .build()

    @Inject
    lateinit var mockServerManager: MockServerManager

    @Inject
    lateinit var urlInterceptorHolder: UrlInterceptorHolder

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)

        (urlInterceptorHolder.urlInterceptor as? DebugUrlInterceptor)
            ?.setMockServerManger(mockServerManager)
    }

    override fun applicationInjector() = appComponent
}