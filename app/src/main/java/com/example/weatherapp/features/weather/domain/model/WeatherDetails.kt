package com.example.weatherapp.features.weather.domain.model

data class WeatherDetails(
    val name: String,
    val temperature: String,
    val humidity: String,
    val rain: String,
    val surfacePressure: String,
    val windSpeed: String,
    val windDirection: String
)