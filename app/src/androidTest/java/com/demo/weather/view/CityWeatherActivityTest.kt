package com.demo.weather.view

import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import org.junit.Rule
import androidx.test.core.app.ApplicationProvider
import android.content.Intent
import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.demo.weather.NETWORK_DELAY
import com.demo.weather.R
import com.demo.weather.util.CITY_ID
import org.hamcrest.Matchers.not
import org.junit.Test


@LargeTest
@RunWith(AndroidJUnit4::class)
class CityWeatherActivityTest {

    private val intent: Intent
    get()
    {
        val intent = Intent(ApplicationProvider.getApplicationContext(), CityWeatherActivity::class.java)
        intent.putExtra(CITY_ID, "Singapore")
        return intent
    }

    @get:Rule
    public var activityScenarioRule: ActivityScenarioRule<CityWeatherActivity> = ActivityScenarioRule(intent)


    @Test
    fun testLaunch() {
        SystemClock.sleep(NETWORK_DELAY)
        onView(withId(R.id.weather_desc)).check(matches(not(withText(""))))
        onView(withId(R.id.temperature)).check(matches(not(withText(""))))
        onView(withId(R.id.city_name)).check(matches(not(withText(""))))
        onView(withId(R.id.humidity)).check(matches(not(withText(""))))
        onView(withId(R.id.weather_icon)).check(matches(isDisplayed()))
    }

}