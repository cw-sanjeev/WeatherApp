package com.example.weatherapp.core.domain.remote

import kotlinx.coroutines.flow.Flow

interface NetworkHelper {
    fun isNetworkConnected(): Boolean
    fun observeNetworkStatus(): Flow<Boolean>
}