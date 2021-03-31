package com.demo.weather

import com.demo.weather.di.DaggerAppComponent
import dagger.android.DaggerApplication

@Suppress("unused")
open class DemoApplication : DaggerApplication() {
    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun applicationInjector() = appComponent

}