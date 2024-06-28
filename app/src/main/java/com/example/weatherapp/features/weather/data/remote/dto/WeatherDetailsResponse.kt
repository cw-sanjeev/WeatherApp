package com.example.weatherapp.features.weather.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WeatherDetailsResponse(
    @SerializedName("latitude") var latitude: Double? = null,
    @SerializedName("longitude") var longitude: Double? = null,
    @SerializedName("generationtime_ms") var generationtimeMs: Double? = null,
    @SerializedName("utc_offset_seconds") var utcOffsetSeconds: Int? = null,
    @SerializedName("timezone") var timezone: String? = null,
    @SerializedName("timezone_abbreviation") var timezoneAbbreviation: String? = null,
    @SerializedName("elevation") var elevation: Int? = null,
    @SerializedName("current_units") var currentUnits: CurrentUnits? = CurrentUnits(),
    @SerializedName("current") var current: Current? = Current()
)
