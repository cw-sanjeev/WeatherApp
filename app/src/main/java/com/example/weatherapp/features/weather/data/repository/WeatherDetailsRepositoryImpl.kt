package com.example.weatherapp.features.weather.data.repository

import com.example.weatherapp.features.weather.data.mapper.toWeatherDetail
import com.example.weatherapp.features.weather.data.remote.WeatherApiService
import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.model.WeatherDetails
import com.example.weatherapp.features.weather.domain.repository.WeatherDetailRepository
import javax.inject.Inject

class WeatherDetailsRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService): WeatherDetailRepository {
    override suspend fun getWeatherDetailsForCity(cityList: City): Result<WeatherDetails> {
        return try {
            val weatherDetailsResponse = weatherApiService.getWeatherDetailsForCity("https://api.open-meteo.com/v1/forecast?latitude=${cityList.lat}&longitude=${cityList.long}&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,precipitation,rain,showers,snowfall,weather_code,cloud_cover,pressure_msl,surface_pressure,wind_speed_10m,wind_direction_10m,wind_gusts_10m&forecast_days=14")
            val weatherDetails = weatherDetailsResponse.toWeatherDetail(cityList.name)
            Result.success(weatherDetails)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}