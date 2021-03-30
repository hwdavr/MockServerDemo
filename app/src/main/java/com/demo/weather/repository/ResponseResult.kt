package com.demo.weather.repository

// https://medium.com/androiddevelopers/sealed-with-a-class-a906f28ab7b5
sealed class ResponseResult<out T> {
    data class Success<out T>(val data: T?): ResponseResult<T>()
    data class Error(val type: ErrorType): ResponseResult<Nothing>()
}

enum class ErrorType {
    GENERIC,
    EXCEPTION
}
