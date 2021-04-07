package com.demo.weather.view

import android.os.SystemClock.sleep
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.BaseUiMockServerTest
import com.demo.weather.LAUNCH_DELAY
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.screen.HomeScreen.recyclerView
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest : BaseUiMockServerTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeScreenActivity::class.java, false, false)

    @Test
    fun testCityListNotEmpty() {
        run {
            mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
            mActivityTestRule.launchActivity(null)
            sleep(LAUNCH_DELAY)
            recyclerView {
                isVisible()
                hasSize(5)
            }
        }
    }

    @Test
    fun testCityListIsEmpty() {
        run {
            mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.FAILURE)
            mActivityTestRule.launchActivity(null)
            sleep(LAUNCH_DELAY)
            recyclerView {
                isVisible()
                hasSize(0)
            }
        }
    }
}

