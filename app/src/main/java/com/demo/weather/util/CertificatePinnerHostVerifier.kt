package com.demo.weather.util

import okhttp3.CertificatePinner
import okhttp3.HttpUrl

class CertificatePinnerHostVerifier(
    private val certificatePinner: CertificatePinner
) {
    /**
     * Verify if the current url is matching one of the host you set in CertificatePinner
     */
    fun verify(url: HttpUrl): Boolean {
        certificatePinner
            .pins
            .forEach {
                if (it.matchesHostname(url.host)) return true
            }
        return false
    }
}
