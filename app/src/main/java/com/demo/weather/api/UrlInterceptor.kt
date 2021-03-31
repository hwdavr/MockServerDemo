package com.demo.weather.api

import com.demo.weather.mockserver.MockServerManager
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.IOException


class UrlInterceptor(
    private val mockServerManager: MockServerManager
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (mockServerManager.shouldMockApi(request.url.encodedPath)) {
            val newUrl: HttpUrl = request.url.newBuilder()
                .host(MockServerManager.HOST)
                .port(MockServerManager.PORT)
                .build()
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}