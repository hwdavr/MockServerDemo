package com.demo.weather.view.base

import androidx.annotation.MainThread
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider

/**
 * Returns a property delegate to access activity's [ViewModel],
 * if [factoryProducer] is specified then [ViewModelProvider.Factory]
 * returned by it will be used to create [ViewModel] first time.
 *
 * ```
 * class MyActivity : Activity() {
 *     val viewmodel: MyViewModel by viewModels()
 * }
 * ```
 *
 * This code was copied from https://android.googlesource.com/platform/frameworks/support/+/androidx-master-dev/activity/ktx/src/main/java/androidx/activity/ActivityViewModelLazy.kt
 * and is available in the alpha versions of the new Androidx KTX libraries. Once we switch to
 * KTX, we will replace this code with the library version.
 */
@MainThread
inline fun <reified VM : ViewModel> FragmentActivity.viewModels(
    noinline factoryProducer: (() -> ViewModelProvider.Factory)? = null
): Lazy<VM> {
    val factoryPromise = factoryProducer ?: {
        val application = application ?: throw IllegalArgumentException(
            "ViewModel can be accessed only when Activity is attached"
        )
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
    return ViewModelLazy(VM::class, { viewModelStore }, factoryPromise)
}
