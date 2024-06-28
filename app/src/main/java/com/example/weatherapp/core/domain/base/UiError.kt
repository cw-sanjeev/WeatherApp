package com.example.weatherapp.core.domain.base

sealed class UiError {
    data class NoInternet(val msg: String): UiError()
    data class SomethingWentWrong(val msg: String): UiError()
}