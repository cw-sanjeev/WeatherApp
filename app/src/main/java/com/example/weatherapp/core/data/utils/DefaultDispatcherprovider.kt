package com.example.weatherapp.core.data.utils

import com.example.weatherapp.core.domain.utils.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DefaultDispatcherprovider: DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
}