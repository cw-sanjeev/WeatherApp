package com.example.weatherapp.features.weather.data.mapper

import com.example.weatherapp.features.weather.data.remote.dto.CityResponse
import com.example.weatherapp.features.weather.domain.model.City

fun CityResponse.toCityList(): City? {
    if (name.isNullOrBlank()) return null
    return City(
        name = name!!,
        lat = lat,
        long = lon
    )
}
