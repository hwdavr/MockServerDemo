package com.demo.weather

import org.junit.runners.model.InitializationError
import org.robolectric.RobolectricTestRunner


class RobolectricGradleTestRunner @Throws(InitializationError::class)
constructor(testClass: Class<*>) : RobolectricTestRunner(testClass) {

    init {
        val buildVariant =
            (if (BuildConfig.FLAVOR.isEmpty()) "" else BuildConfig.FLAVOR + "/") + BuildConfig.BUILD_TYPE
        var intermediatesPath =
            BuildConfig::class.java.getResource("")!!.toString().replace("file:", "")
        intermediatesPath = intermediatesPath.substring(0, intermediatesPath.indexOf("/classes"))

        System.setProperty("android.package", BuildConfig.APPLICATION_ID)
        System.setProperty(
            "android.manifest",
            "$intermediatesPath/manifests/full/$buildVariant/AndroidManifest.xml"
        )
        System.setProperty("android.resources", "$intermediatesPath/res/$buildVariant")
        System.setProperty("android.assets", "$intermediatesPath/assets/$buildVariant")
    }

}
