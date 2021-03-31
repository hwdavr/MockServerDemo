package com.demo.weather

import androidx.test.core.app.ApplicationProvider
import androidx.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseUiMockServerTest {
    private val app = ApplicationProvider.getApplicationContext() as DebugApplication
    private val mockServerManager = app.mockServerManager

    @Before
    fun setUp() {
        startMockServer()
    }

    private fun startMockServer() {
        mockServerManager.startServer()
    }


}