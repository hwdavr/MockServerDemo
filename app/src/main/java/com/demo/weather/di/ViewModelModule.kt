package com.demo.weather.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.viewmodel.CityWeatherViewModel
import com.demo.weather.viewmodel.HomeScreenViewModel
import com.demo.weather.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
internal abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeScreenViewModel::class)
    internal abstract fun bindHomeScreenActivityViewModel(homeScreenViewModel: HomeScreenViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(CityWeatherViewModel::class)
    internal abstract fun bindCityWeatherActivityViewModel(cityWeatherViewModel: CityWeatherViewModel): ViewModel

}