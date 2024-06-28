package com.example.weatherapp.features.weather.data.remote.dto

import com.google.gson.annotations.SerializedName


data class Current(
    @SerializedName("time") var time: String? = null,
    @SerializedName("interval") var interval: Int? = null,
    @SerializedName("temperature_2m") var temperature2m: Double? = null,
    @SerializedName("relative_humidity_2m") var relativeHumidity2m: Int? = null,
    @SerializedName("apparent_temperature") var apparentTemperature: Double? = null,
    @SerializedName("is_day") var isDay: Int? = null,
    @SerializedName("precipitation") var precipitation: Int? = null,
    @SerializedName("rain") var rain: Int? = null,
    @SerializedName("showers") var showers: Int? = null,
    @SerializedName("snowfall") var snowfall: Int? = null,
    @SerializedName("weather_code") var weatherCode: Int? = null,
    @SerializedName("cloud_cover") var cloudCover: Int? = null,
    @SerializedName("pressure_msl") var pressureMsl: Double? = null,
    @SerializedName("surface_pressure") var surfacePressure: Double? = null,
    @SerializedName("wind_speed_10m") var windSpeed10m: Double? = null,
    @SerializedName("wind_direction_10m") var windDirection10m: Int? = null,
    @SerializedName("wind_gusts_10m") var windGusts10m: Double? = null
)
