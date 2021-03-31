package com.demo.weather

import com.demo.weather.mockserver.MockServerManager
import javax.inject.Inject

class DebugApplication : DemoApplication() {
    @Inject
    lateinit var mockServerManager: MockServerManager
}