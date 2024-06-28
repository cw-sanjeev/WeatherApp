package com.example.weatherapp.core.domain.local.preferences

import com.example.weatherapp.core.domain.model.Location

interface Preferences {
    fun saveLocation(location: Location)
    fun getLocation(): Location?

    companion object {
        const val SHARED_PREF = "shared_pref"
        const val KEY_LOCATION = "location"
    }
}