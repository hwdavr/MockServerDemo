package com.demo.weather.api

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException

open class UrlInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        return chain.proceed(request)
    }
}