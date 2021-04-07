package com.demo.weather.screen

import com.agoda.kakao.image.KImageView
import com.agoda.kakao.text.KTextView
import com.demo.weather.R
import com.demo.weather.view.CityWeatherActivity
import com.kaspersky.kaspresso.screens.KScreen

object CityWeatherScreen : KScreen<CityWeatherScreen>() {
    override val layoutId: Int? = R.layout.activity_weather
    override val viewClass: Class<*>? = CityWeatherActivity::class.java

    val weatherDesc = KTextView { withId(R.id.weather_desc) }
    val temperature = KTextView { withId(R.id.temperature) }
    val cityName = KTextView { withId(R.id.city_name) }
    val humidity = KTextView { withId(R.id.humidity) }
    val weatherIcon = KImageView { withId(R.id.weather_icon) }
}