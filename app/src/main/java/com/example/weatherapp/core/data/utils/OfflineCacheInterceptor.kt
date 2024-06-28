package com.example.weatherapp.core.data.utils

import com.example.weatherapp.core.domain.AppConstants
import com.example.weatherapp.core.domain.remote.NetworkHelper
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

class OfflineCacheInterceptor(private val networkHelper: NetworkHelper): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        if (!networkHelper.isNetworkConnected()) {
            builder.cacheControl(CacheControl.FORCE_CACHE)
            builder.addHeader("Network-Status", AppConstants.NetworkStatus.OFFLINE.name)
        }
        return chain.proceed(builder.build())
    }
}