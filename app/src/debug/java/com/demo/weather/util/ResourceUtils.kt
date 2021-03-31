package com.demo.weather.util

import okio.buffer
import okio.source
import java.io.IOException
import java.nio.charset.StandardCharsets


object ResourceUtils {
    /**
     * Helper function which will load JSON from
     * the path specified
     *
     * @param path : Path of JSON file
     * @return json : JSON from file at given path
     */
    fun getJsonString(path : String) : String {
        // Load the JSON response
        return try {
            this.javaClass
                .classLoader
                ?.getResourceAsStream(path)
                ?.source()
                ?.buffer()
                ?.readString(StandardCharsets.UTF_8)
                .orEmpty()
        } catch (e: IOException) {
            ""
        }
    }
}