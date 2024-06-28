package com.example.weatherapp.features.weather.data.mapper

import com.example.weatherapp.features.weather.data.remote.dto.WeatherDetailsResponse
import com.example.weatherapp.features.weather.domain.model.WeatherDetails

fun WeatherDetailsResponse.toWeatherDetail(name: String): WeatherDetails {
    return WeatherDetails(
        name = name,
        temperature = (current?.temperature2m.toString() + " " + currentUnits?.temperature2m) ?: "",
        humidity = (current?.relativeHumidity2m.toString() + " " + currentUnits?.relativeHumidity2m)
            ?: "",
        rain = (current?.rain.toString() + " " + currentUnits?.rain) ?: "",
        surfacePressure = (current?.surfacePressure.toString() + " " + currentUnits?.surfacePressure)
            ?: "",
        windSpeed = (current?.windSpeed10m.toString() + " " + currentUnits?.windSpeed10m) ?: "",
        windDirection = (current?.windDirection10m.toString() + " " + currentUnits?.windDirection10m)
            ?: " "
    )
}