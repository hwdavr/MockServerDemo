package com.demo.weather.view

import android.content.Intent
import android.os.SystemClock
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.BaseUiMockServerTest
import com.demo.weather.LAUNCH_DELAY
import com.demo.weather.R
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.CITY_ID
import com.demo.weather.util.WEATHER_API_CURRENT_WEATHER_URL
import org.hamcrest.Matchers.not
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
        mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.SUCCESS)
        activityScenario = ActivityScenario.launch(intent)
        SystemClock.sleep(LAUNCH_DELAY)
        onView(withId(R.id.weather_desc)).check(matches(withText("Partly cloudy")))
        onView(withId(R.id.temperature)).check(matches(withText("26.0")))
        onView(withId(R.id.city_name)).check(matches(withText("Singapore")))
        onView(withId(R.id.humidity)).check(matches(withText("Humidity: null")))
        onView(withId(R.id.weather_icon)).check(matches(isDisplayed()))
    }

    @Test
    fun testLaunchFailure() {
        mockServerManager.enableApi(WEATHER_API_CURRENT_WEATHER_URL, MockScenarios.FAILURE)
        activityScenario = ActivityScenario.launch(intent)
        SystemClock.sleep(LAUNCH_DELAY)
        onView(withId(R.id.weather_desc)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.temperature)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.humidity)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.weather_icon)).check(matches(not(isDisplayed())))
    }

}