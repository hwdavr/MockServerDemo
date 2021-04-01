package com.demo.weather

import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.IdlingRegistry
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.After
import org.junit.Before

open class BaseUiMockServerTest {
    protected val app = ApplicationProvider.getApplicationContext() as DebugApplication
    protected val mockServerManager = app.mockServerManager

    @Before
    open fun setUp() {
        mockServerManager.startServer()
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