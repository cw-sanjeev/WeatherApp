package com.example.weatherapp.features.weather.data.repository

import com.example.weatherapp.core.domain.AppConstants
import com.example.weatherapp.core.domain.exceptions.NoInternetException
import com.example.weatherapp.features.weather.data.mapper.toCityList
import com.example.weatherapp.features.weather.data.remote.WeatherApiService
import com.example.weatherapp.features.weather.domain.model.City
import com.example.weatherapp.features.weather.domain.repository.WeatherListRepository
import javax.inject.Inject

class WeatherListRepositoryImpl @Inject constructor(private val weatherApiService: WeatherApiService) :
    WeatherListRepository {
    override suspend fun getCityList(): Result<List<City>?> {
        return try {
            val cityListResponse =
                weatherApiService.getCityList("https://api.openweathermap.org/geo/1.0/direct?q=India&limit=50&appid=caea47f25a8909b1176552e1aa58d2c2")
            if (cityListResponse.isSuccessful) {
                val networkStatus = cityListResponse.raw().header("Network-Status")
                if (networkStatus == AppConstants.NetworkStatus.OFFLINE.name) {
                    Result.failure<RuntimeException>(NoInternetException("No Internet Connection"))
                }
                val cityList = cityListResponse?.body()?.mapNotNull { city -> city.toCityList() }
                Result.success(cityList)
            }
            Result.success(null)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}