package com.demo.weather.di

import com.demo.weather.view.CityWeatherActivity
import com.demo.weather.view.HomeScreenActivity
import com.demo.weather.view.SettingsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    internal abstract fun contributeHomeScreenActivity(): HomeScreenActivity

    @ContributesAndroidInjector
    internal abstract fun contributeCityWeatherActivity(): CityWeatherActivity

    @ContributesAndroidInjector
    internal abstract fun contributeSettingsActivity(): SettingsActivity
}
