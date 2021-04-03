package com.demo.weather.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.demo.weather.repository.CityWeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CityWeatherViewModel  @Inject
constructor(
    private val repository: CityWeatherRepo
): BaseViewModel() {
    private val TAG = CityWeatherViewModel::class.java.simpleName

    val weatherVisibility: MutableLiveData<Int> = MutableLiveData()

    val cityLiveData: MediatorLiveData<String> = MediatorLiveData()
    val weatherIconUrlLiveData: MutableLiveData<String> = MutableLiveData()
    val temperatureLiveData: MediatorLiveData<String> = MediatorLiveData()
    val weatherDescLiveData: MutableLiveData<String> = MutableLiveData()
    val humidityLiveData: MutableLiveData<String> = MutableLiveData()
    val updatedTimeLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Log.d(TAG, "Injection discovered")
        weatherVisibility.value = View.INVISIBLE
    }

    fun checkCurrentWeather(city: String) {
        cityLiveData.value = city
        weatherVisibility.value = View.INVISIBLE
        runOnMain {
            repository.currentWeather(city)
                .flowOn(Dispatchers.IO)
                .catch {
                    handleResult(false)
                }
                .collect {
                    it?.let { obj ->
                        temperatureLiveData.value = obj.temp_c
                        weatherDescLiveData.value = obj.condition?.description
                        humidityLiveData.value = "Humidity: ${obj.humidity}"
                        updatedTimeLiveData.value = "Last updated: ${obj.last_updated}"
                        weatherIconUrlLiveData.value = "https:${obj.condition?.icon}"
                    }

                    weatherVisibility.value = View.VISIBLE
                    handleResult(true)
                }
        }
    }
}
