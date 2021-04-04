package com.demo.weather.api

import com.demo.weather.mock.mockserver.MockServerManager
import com.demo.weather.mock.mockserver.MockServerManager.Companion.HTTPS_SCHEME
import com.demo.weather.util.CertificatePinnerHostVerifier
import com.demo.weather.util.ENABLE_SSL_PINNING
import okhttp3.*
import okio.IOException

class DebugUrlInterceptor : Interceptor {
    var mockServerManager: MockServerManager? = null
    var certificatePinner: CertificatePinner? = null

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()
        if (mockServerManager?.shouldMockApi(request.url.encodedPath) == true) {
            // Only validate the host name when you mock the API
            if (ENABLE_SSL_PINNING) {
                certificatePinner
                    ?.let {
                        if (!CertificatePinnerHostVerifier(it).verify(request.url)) {
                            throw IOException("The host doesn't match any host you added in CertificatePinner")
                        }
                    } ?: throw IOException("CertificatePinner is not set")
            }
            val newUrl: HttpUrl = request.url.newBuilder()
                .scheme(mockServerManager?.scheme ?: HTTPS_SCHEME)
                .host(MockServerManager.HOST)
                .port(mockServerManager?.port ?: 1)
                .build()
            request = request.newBuilder()
                .url(newUrl)
                .build()
        }
        return chain.proceed(request)
    }
}