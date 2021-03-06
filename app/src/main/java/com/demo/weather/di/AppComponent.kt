package com.demo.weather.di

import android.app.Application
import com.demo.weather.DemoApplication
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
    ActivityModule::class,
    ViewModelModule::class,
    NetworkModule::class])
interface AppComponent: AndroidInjector<DemoApplication> {

    override fun inject(instance: DemoApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}