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
        urlInterceptor.setMockServerManger(mockServerManager)
        mockServerManager.startServer()
    }

    @After
    open fun tearDown() {
        mockServerManager.stopServer()
    }
}