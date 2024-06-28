package com.example.weatherapp.core.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import com.example.weatherapp.core.domain.remote.NetworkHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkHelperImpl constructor(private val connectivityManager: ConnectivityManager, private val context: Context) : NetworkHelper {

    private val networkStatusFlow = MutableStateFlow(isNetworkConnected())

    init {
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                networkStatusFlow.value = true
            }

            override fun onLost(network: Network) {
                networkStatusFlow.value = false
            }
        }
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun isNetworkConnected(): Boolean {
        var result = false
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    override fun observeNetworkStatus(): Flow<Boolean> = networkStatusFlow
}