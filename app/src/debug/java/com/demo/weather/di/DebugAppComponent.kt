package com.demo.weather.di

import android.app.Application
import com.demo.weather.DebugApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Suppress("unused")
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    DebugActivityModule::class,
    ViewModelModule::class,
    DebugNetworkModule::class])
interface DebugAppComponent: AndroidInjector<DebugApplication> {

    override fun inject(instance: DebugApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): DebugAppComponent
    }

}