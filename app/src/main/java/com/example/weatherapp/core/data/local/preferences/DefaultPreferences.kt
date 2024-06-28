package com.example.weatherapp.core.data.local.preferences

import android.content.SharedPreferences
import com.example.weatherapp.core.domain.local.preferences.Preferences
import com.example.weatherapp.core.domain.model.Location
import com.google.gson.Gson
import javax.inject.Inject

class DefaultPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
): Preferences {
    override fun saveLocation(location: Location) {
        sharedPreferences.edit()
            .putString(Preferences.KEY_LOCATION, gson.toJson(location))
            .apply()
    }

    override fun getLocation(): Location? {
        val locationString = sharedPreferences.getString(Preferences.KEY_LOCATION, null)
        return gson.fromJson(locationString, Location::class.java)
    }
}