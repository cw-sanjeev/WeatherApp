package com.example.weatherapp.core.data.utils

import android.util.Log
import com.example.weatherapp.core.domain.utils.Logger

class AppLogger: Logger {
    override fun d(tag: String, msg: String) {
        Log.d(tag, msg)
    }
}