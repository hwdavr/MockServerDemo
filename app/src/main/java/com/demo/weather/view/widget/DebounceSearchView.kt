package com.demo.weather.view.widget

import androidx.appcompat.widget.SearchView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.whileSelect


fun SearchView.onTextChanged(): ReceiveChannel<String> =
    Channel<String>(capacity = Channel.UNLIMITED).also { channel ->
        setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                text.orEmpty().let {
                    if (it.isNotEmpty()) {
                        channel.offer(it)
                    }
                }
                return false
            }

        })
    }

fun <T> ReceiveChannel<T>.debounce(time: Long): ReceiveChannel<T> =
    Channel<T>(capacity = Channel.CONFLATED).also { channel ->
        GlobalScope.launch {
            var value = receive()
            whileSelect {
                onTimeout(time) {
                    channel.offer(value)
                    value = receive()
                    true
                }
                onReceive {
                    value = it
                    true
                }
            }
        }
    }

