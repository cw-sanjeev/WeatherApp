package com.example.weatherapp.core.presentation.utils

import android.widget.ImageView
import com.bumptech.glide.RequestManager
import com.example.weatherapp.core.domain.utils.ImageLoader

class GlideImageLoader(private val glideRequestManager: RequestManager): ImageLoader {
    override fun load(url: String, view: ImageView) {
        glideRequestManager
            .load(url)
            .centerCrop()
            .into(view)
    }
}