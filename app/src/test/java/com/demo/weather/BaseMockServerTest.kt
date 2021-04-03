package com.demo.weather

import com.demo.weather.api.DebugUrlInterceptor
import com.demo.weather.api.UrlInterceptorHolder
import com.demo.weather.di.NetworkModule
import com.demo.weather.mock.mockserver.MockServerManager
import org.junit.After
import org.junit.Before


open class BaseMockServerTest {
    val mockServerManager = MockServerManager()
    private val urlInterceptor = DebugUrlInterceptor()
    val retrofit = NetworkModule()
        .provideRetrofit(
            NetworkModule()
                .provideOkHttpClient(
                    UrlInterceptorHolder(
                        urlInterceptor
                    )
                )
        )

    @Before
    open fun setUp() {
        // Fixed to use http scheme, because JUnit test is using JKS format keystore instead of
        // BKS in Android
        mockServerManager.scheme = MockServerManager.HTTP_SCHEME
        urlInterceptor.setMockServerManger(mockServerManager)
        mockServerManager.startServer()
    }

    @After
    open fun tearDown() {
        mockServerManager.stopServer()
    }
}