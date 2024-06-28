package com.example.weatherapp.features.weather.domain.usecase

import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.model.WeatherDetails
import com.example.weatherapp.features.weather.domain.repository.WeatherDetailRepository

class GetWeatherDetailUseCase(
    private val repository: WeatherDetailRepository
) {
    suspend operator fun invoke(cityList: City): Result<WeatherDetails> {
        return repository.getWeatherDetailsForCity(cityList)
    }
}