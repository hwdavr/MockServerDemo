package com.demo.weather.view

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.BaseUiMockServerTest
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.screen.CityWeatherScreen.cityName
import com.demo.weather.screen.CityWeatherScreen.humidity
import com.demo.weather.screen.CityWeatherScreen.temperature
import com.demo.weather.screen.CityWeatherScreen.weatherDesc
import com.demo.weather.screen.CityWeatherScreen.weatherIcon
import com.demo.weather.util.CITY_ID
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CityWeatherActivityTest : BaseUiMockServerTest() {

    private val intent: Intent
        get() {
            val intent =
                Intent(ApplicationProvider.getApplicationContext(), CityWeatherActivity::class.java)
            intent.putExtra(CITY_ID, "Singapore")
            return intent
        }

    lateinit var activityScenario: ActivityScenario<CityWeatherActivity>

    @After
    override fun tearDown() {
        super.tearDown()
        activityScenario.close()
    }

    @Test
    fun testLaunchSuccess() {
        run {
            step("") {
                mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.SUCCESS)
                activityScenario = ActivityScenario.launch(intent)
                weatherDesc.hasText("Partly cloudy")
                temperature.hasText("26.0")
                cityName.hasText("Singapore")
                humidity.hasText("Humidity: null")
                weatherIcon.isDisplayed()
            }
        }
    }

    @Test
    fun testLaunchFailure() {
        run {
            step("") {
                mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.FAILURE)
                activityScenario = ActivityScenario.launch(intent)
                weatherDesc.hasEmptyText()
                temperature.hasEmptyText()
                cityName.hasText("Singapore")
                humidity.hasEmptyText()
                weatherIcon.isNotDisplayed()
            }
        }
    }

}