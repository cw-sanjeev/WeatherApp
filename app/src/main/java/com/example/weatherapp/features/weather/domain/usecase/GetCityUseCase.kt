package com.example.weatherapp.features.weather.domain.usecase

import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.repository.WeatherListRepository
import javax.inject.Inject

class GetCityUseCase(private val repository: WeatherListRepository) {
    suspend operator fun invoke(): Result<List<City>?> = repository.getCityList()
}