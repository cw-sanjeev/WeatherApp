package com.example.weatherapp.features.weather.domain.repository

import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.model.WeatherDetails

interface WeatherDetailRepository {
    suspend fun getWeatherDetailsForCity(cityList: City): Result<WeatherDetails>
}