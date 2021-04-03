package com.demo.weather.mock.mockserver

import android.util.Log
import com.demo.weather.mock.dispatchers.ApiDispatcher
import com.demo.weather.util.LocalHostSocketFactory
import okhttp3.mockwebserver.MockWebServer

class MockServerManager {
    var port = 1
    private val mockApis = mutableMapOf<String, MockScenarios>()
    private var mockServer: MockWebServer? = null
    private var isServerStarted = false

    fun startServer() {
        if (!isServerStarted) {
            Thread {
                mockServer = MockWebServer()
                mockServer!!.dispatcher = ApiDispatcher(mockApis)
                mockServer!!.start()
                port = mockServer!!.port
                Log.d(TAG, "Started mock server at: ${mockServer!!.url("")}")
                isServerStarted = true
            }.start()
        }
    }

    fun startSslServer() {
        if (!isServerStarted) {
            Thread {
                mockServer = MockWebServer()
                mockServer!!.useHttps(LocalHostSocketFactory.getSocketFactory(), false)
                mockServer!!.dispatcher = ApiDispatcher(mockApis)
                mockServer!!.start()
                port = mockServer!!.port
                Log.d(TAG, "Started mock ssl server at: ${mockServer!!.url("")}")
                isServerStarted = true
            }.start()
        }
    }

    fun stopServer() {
        isServerStarted = false
        mockServer?.shutdown()
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
        const val HTTP_SCHEME = "http"
        const val HTTPS_SCHEME = "https"
    }
}