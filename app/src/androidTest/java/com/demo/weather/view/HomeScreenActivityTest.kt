package com.demo.weather.view

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.BaseUiMockServerTest
import com.demo.weather.R
import com.demo.weather.mock.mockserver.MockScenarios
import com.demo.weather.util.WEATHER_API_SEARCH_URL
import org.junit.Assert.assertTrue
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
        mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.SUCCESS)
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        assertTrue(getListcount() == 5)
    }

    @Test
    fun testCityListIsEmpty() {
        mockServerManager.enableApi(WEATHER_API_SEARCH_URL, MockScenarios.FAILURE)
        mActivityTestRule.launchActivity(null)
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        assertTrue(getListcount() == 0)
    }

    private fun getListcount(): Int {
        val recyclerView =
            mActivityTestRule.getActivity().findViewById(R.id.recycler_view) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

}

