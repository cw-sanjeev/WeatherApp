package com.example.weatherapp.features.weather.data.remote.dto

import com.google.gson.annotations.SerializedName


data class CityResponse(
    @SerializedName("name") var name: String? = null,
    @SerializedName("lat") var lat: Double = 0.0,
    @SerializedName("lon") var lon: Double = 0.0,
    @SerializedName("country") var country: String? = null,
    @SerializedName("state") var state: String? = null
)