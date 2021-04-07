package com.demo.weather.view

import android.os.SystemClock
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.BaseUiMockServerTest
import com.demo.weather.BuildConfig
import com.demo.weather.LAUNCH_DELAY
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.screen.HomeScreen
import com.demo.weather.util.ENABLE_SSL_PINNING
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import org.junit.Assume
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeScreenActivitySslFailureTest : BaseUiMockServerTest() {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeScreenActivity::class.java, false, false)

    override fun startMockServer() {
        Assume.assumeTrue(ENABLE_SSL_PINNING && BuildConfig.DEBUG)
        mockServerManager.startSslServer("bad.keystore.jks")
    }

    @Test
    fun testCityListIsEmptyBecauseSslPinningFailure() {
        run {
            mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
            mActivityTestRule.launchActivity(null)
            SystemClock.sleep(LAUNCH_DELAY)
            HomeScreen.recyclerView {
                isVisible()
                hasSize(0)
            }
        }
    }
}

