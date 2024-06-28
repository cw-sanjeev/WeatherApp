package com.example.weatherapp.core.data.remote

import com.example.weatherapp.core.domain.remote.NetworkService
import retrofit2.Retrofit

class RetrofitNetworkService(private val retrofit: Retrofit): NetworkService {
    override fun <T> create(service: Class<T>): T {
        return retrofit.create(service)
    }
}