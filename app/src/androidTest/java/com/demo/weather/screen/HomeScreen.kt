package com.demo.weather.screen

import androidx.test.espresso.matcher.ViewMatchers.withId
import com.agoda.kakao.recycler.KRecyclerView
import com.demo.weather.R
import com.demo.weather.view.HomeScreenActivity
import com.kaspersky.kaspresso.screens.KScreen
import kotlinx.android.synthetic.main.activity_main.*

object HomeScreen : KScreen<HomeScreen>() {
    override val layoutId: Int? = R.layout.activity_main
    override val viewClass: Class<*>? = HomeScreenActivity::class.java

    val recyclerView = KRecyclerView( { withId(R.id.recycler_view) }, itemTypeBuilder = { })
}