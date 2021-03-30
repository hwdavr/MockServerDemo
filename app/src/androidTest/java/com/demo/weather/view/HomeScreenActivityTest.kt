package com.demo.weather.view

import android.os.SystemClock
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.demo.weather.NETWORK_DELAY
import com.demo.weather.R
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class HomeScreenActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(HomeScreenActivity::class.java)

    @Test
    fun testCityListNotEmpty() {
        SystemClock.sleep(NETWORK_DELAY)
        assertTrue(getListcount() > 0)
    }

    private fun getListcount(): Int {
        val recyclerView =
            mActivityTestRule.getActivity().findViewById(R.id.recycler_view) as RecyclerView
        return recyclerView.adapter!!.itemCount
    }

}

