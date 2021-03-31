package com.demo.weather.api

import okhttp3.Interceptor

class UrlInterceptorHolder(
    val urlInterceptor: Interceptor
)