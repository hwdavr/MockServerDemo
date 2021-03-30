package com.demo.weather.view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.weather.R

class CityViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val cityName: TextView = view.findViewById(R.id.city_name)

    interface Delegate {
        fun onItemClick(city: String, view: View)
    }
}