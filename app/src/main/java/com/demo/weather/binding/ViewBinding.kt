package com.demo.weather.binding

import android.view.View
import android.view.View.*
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.demo.weather.view.widget.MultiStatus

@BindingAdapter("weatherIcon")
fun loadImage(view: ImageView, weatherIconUrl: String) {
    Glide.with(view.getContext())
        .load(weatherIconUrl)
        .into(view)
}

@BindingAdapter("visibleOrGone")
fun View.setVisibleOrGone(show: Boolean) {
    visibility = if (show) VISIBLE else GONE
}

@BindingAdapter("visible")
fun View.setVisible(show: Boolean) {
    visibility = if (show) VISIBLE else INVISIBLE
}

@BindingAdapter("isLoading")
fun ProgressBar.isLoading(status: MultiStatus?) {
    visibility = if (status == MultiStatus.LOADING) VISIBLE else INVISIBLE
}

@BindingAdapter("isError")
fun ImageView.isError(status: MultiStatus?) {
    visibility = if (status == MultiStatus.ERROR) VISIBLE else INVISIBLE
}
