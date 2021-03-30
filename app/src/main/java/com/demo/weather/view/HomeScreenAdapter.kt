package com.demo.weather.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.weather.R
import com.demo.weather.model.City

class HomeScreenAdapter(private var cities: List<City>): RecyclerView.Adapter<CityViewHolder>() {

    var delegate: CityViewHolder.Delegate? = null

    fun updateData(data: List<City>) {
        cities = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val city = cities[position]

        holder.cityName.text = city.name
        holder.view.setOnClickListener {
            delegate?.onItemClick(city.name, holder.view)
        }
    }
}
