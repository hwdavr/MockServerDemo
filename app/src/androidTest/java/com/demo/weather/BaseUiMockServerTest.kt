package com.demo.weather

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import com.demo.weather.util.ENABLE_SSL_PINNING
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.After
import org.junit.Before

open class BaseUiMockServerTest {
    protected val app = ApplicationProvider.getApplicationContext() as DebugApplication
    protected val mockServerManager = app.mockServerManager

    open fun startMockServer() {
        if (ENABLE_SSL_PINNING && BuildConfig.DEBUG) {
            mockServerManager.startSslServer()
        } else {
            mockServerManager.startServer()
        }
    }

    @Before
    open fun setUp() {
        startMockServer()
        IdlingRegistry.getInstance().register(
            OkHttp3IdlingResource.create(
                "okhttp",
                app.okHttpClient
            ))
    }

    @After
    open fun tearDown() {
        mockServerManager.stopServer()
    }
}