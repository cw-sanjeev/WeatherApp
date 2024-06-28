package com.example.weatherapp.features.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrentUnits(
    @SerializedName("time") var time: String? = null,
    @SerializedName("interval") var interval: String? = null,
    @SerializedName("temperature_2m") var temperature2m: String? = null,
    @SerializedName("relative_humidity_2m") var relativeHumidity2m: String? = null,
    @SerializedName("apparent_temperature") var apparentTemperature: String? = null,
    @SerializedName("is_day") var isDay: String? = null,
    @SerializedName("precipitation") var precipitation: String? = null,
    @SerializedName("rain") var rain: String? = null,
    @SerializedName("showers") var showers: String? = null,
    @SerializedName("snowfall") var snowfall: String? = null,
    @SerializedName("weather_code") var weatherCode: String? = null,
    @SerializedName("cloud_cover") var cloudCover: String? = null,
    @SerializedName("pressure_msl") var pressureMsl: String? = null,
    @SerializedName("surface_pressure") var surfacePressure: String? = null,
    @SerializedName("wind_speed_10m") var windSpeed10m: String? = null,
    @SerializedName("wind_direction_10m") var windDirection10m: String? = null,
    @SerializedName("wind_gusts_10m") var windGusts10m: String? = null
)