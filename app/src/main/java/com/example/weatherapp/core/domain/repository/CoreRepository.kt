package com.example.weatherapp.core.domain.repository

import com.example.weatherapp.core.domain.model.Location

interface CoreRepository {
    suspend fun getLocation(): Result<Location?>
    suspend fun saveLocation(lat: Double, long: Double)
}