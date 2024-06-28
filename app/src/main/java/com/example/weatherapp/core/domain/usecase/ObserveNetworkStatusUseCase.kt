package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.domain.remote.NetworkHelper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveNetworkStatusUseCase @Inject constructor(
    private val networkHelper: NetworkHelper
) {
    operator fun invoke(): Flow<Boolean> = networkHelper.observeNetworkStatus()
}