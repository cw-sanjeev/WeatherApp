package com.example.weatherapp.features.weather.domain.repository

import com.example.weatherapp.features.weather.domain.model.City

interface WeatherListRepository {
    suspend fun getCityList(): Result<List<City>?>
}