package com.example.weatherapp.features.weather.data.remote

import com.example.weatherapp.features.weather.data.remote.dto.CityResponse
import com.example.weatherapp.features.weather.data.remote.dto.WeatherDetailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface WeatherApiService {
    @GET
    suspend fun getCityList(@Url url: String): Response<List<CityResponse>>

    @GET
    suspend fun getWeatherDetailsForCity(@Url url: String): WeatherDetailsResponse
}