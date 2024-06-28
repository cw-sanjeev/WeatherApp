package com.example.weatherapp.features.weather.presentation.citylist

import android.os.Parcel
import android.os.Parcelable
import com.example.weatherapp.core.domain.base.UiError
import com.example.weatherapp.core.domain.base.UiText
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.features.weather.domain.model.City

data class CityListState(
    val cities: List<City>? = null,
    val isLoading: Boolean = true,
    val location: Location? = null,
    val uiError: UiError? = null,
    val useMessage: UiText? = null
)