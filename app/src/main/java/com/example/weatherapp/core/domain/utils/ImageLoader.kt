package com.example.weatherapp.core.domain.utils

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, into: ImageView)
}