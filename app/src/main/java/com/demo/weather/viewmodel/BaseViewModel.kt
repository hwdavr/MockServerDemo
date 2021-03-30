package com.demo.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.weather.view.widget.MultiStatus
import kotlinx.coroutines.*


abstract class BaseViewModel: ViewModel() {
    private val _multiStatus = MutableLiveData<MultiStatus>().apply { MultiStatus.LOADING }
    var multiStatus: LiveData<MultiStatus> = _multiStatus

    protected fun runOnIO(block: suspend (scope: CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            backOnMain {
                showLoading()
            }
            block(this)
        }
    }

    protected fun runOnMain(block: suspend (scope: CoroutineScope) -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            block(this)
        }
    }

    protected suspend fun backOnMain(block: suspend (scope: CoroutineScope) -> Unit) {
        withContext(Dispatchers.Main) {
            block(this)
        }
    }

    protected fun showLoading() {
        _multiStatus.value = MultiStatus.LOADING
    }

    protected fun hideLoading() {
        _multiStatus.value = MultiStatus.NORMAL
    }

    protected fun handleResult(result: Boolean) {
        if (result) _multiStatus.postValue(MultiStatus.NORMAL)
        else _multiStatus.postValue(MultiStatus.ERROR)
    }
}
