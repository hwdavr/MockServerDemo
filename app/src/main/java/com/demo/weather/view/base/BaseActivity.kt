package com.demo.weather.view.base

import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.demo.weather.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    protected abstract val viewModel: BaseViewModel
    protected lateinit var context: Context
    protected lateinit var binding: B

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        context = this
        binding = DataBindingUtil.setContentView(this, getContentView())
        binding.lifecycleOwner = this

        setupBinding()
        setupViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Get ContentView id
     * @return
     */
    @LayoutRes
    protected abstract fun getContentView(): Int

    protected abstract fun setupBinding()

    protected abstract fun setupViewModel()
}
