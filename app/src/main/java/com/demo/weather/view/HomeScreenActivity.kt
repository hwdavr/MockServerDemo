package com.demo.weather.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.weather.R
import com.demo.weather.databinding.ActivityBaseBinding
import com.demo.weather.model.City
import com.demo.weather.util.CITY_ID
import com.demo.weather.util.CITY_NAME_PREFIX
import com.demo.weather.view.base.BaseActivity
import com.demo.weather.view.base.viewModels
import com.demo.weather.view.widget.DividerItemDecorator
import com.demo.weather.viewmodel.HomeScreenViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*


class HomeScreenActivity : BaseActivity<ActivityBaseBinding>() {
    private val TAG = HomeScreenActivity::class.java.simpleName
    override val viewModel by viewModels<HomeScreenViewModel> { viewModelFactory }
    private lateinit var adapter: HomeScreenAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        viewModel.queryCityList(CITY_NAME_PREFIX)
    }

    override fun getContentView() = R.layout.activity_main

    override fun setupBinding() { }

    override fun setupViewModel() {
        viewModel.cities.observe(this, Observer<List<City>> {
            Log.d(TAG, "List updated")
            adapter.updateData(it)
        })
    }

    private fun initUI() {
        adapter = HomeScreenAdapter(viewModel.cities.value ?: emptyList())
        adapter.delegate = object : CityViewHolder.Delegate {
            override fun onItemClick(city: String, view: View) {
                val intent = Intent(context, CityWeatherActivity::class.java)
                intent.putExtra(CITY_ID, city)
                startActivity(intent)
            }
        }
        recycler_view.layoutManager = LinearLayoutManager(this)
        val itemDecorator = DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.divider_default)!!)
        recycler_view.addItemDecoration(itemDecorator)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.clear()
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu, menu)

        val settingMenu = menu.findItem(R.id.action_setting)

        return true
    }

}
