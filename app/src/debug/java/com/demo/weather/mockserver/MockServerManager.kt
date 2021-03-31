package com.demo.weather.mockserver

import android.util.Log
import com.demo.weather.mockserver.dispatchers.ApiDispatcher
import okhttp3.mockwebserver.MockWebServer

class MockServerManager {
    private val mockApis = mutableMapOf<String, MockScenarios>()
    private var isServerStarted = false

    fun enableMockServer() {
        if (!isServerStarted) {
            Thread {
                val mockServer = MockWebServer()
                mockServer.dispatcher = ApiDispatcher(mockApis)
                mockServer.start(PORT)
                Log.d(TAG, "Started mock server at: ${mockServer.url("")}")
                isServerStarted = true
            }.start()
        }
    }

    fun enableApi(api: String, scenarios: MockScenarios) {
        mockApis[api] = scenarios
    }

    fun disableApi(api: String) {
        if (mockApis.contains(api)) {
            mockApis.remove(api)
        }
    }

    fun shouldMockApi(api: String): Boolean {
        mockApis.forEach {
            if (api.contains(it.key)) {
                return true
            }
        }
        return false
    }

    companion object {
        private const val TAG = "MockServerManager"
        const val HOST = "localhost"
        const val PORT = 9000
    }
}