package com.example.weatherapp.core.domain.remote

interface NetworkService {
    fun <T> create(service: Class<T>): T
}