package com.example.weatherapp.core.presentation.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.weatherapp.core.domain.utils.ImageLoader
import com.example.weatherapp.core.presentation.utils.GlideImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CorePresentationModule {
    @Singleton
    @Provides
    fun provideGlideRequestManager(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    @Singleton
    fun provideImageLoader(glideRequestManager: RequestManager): ImageLoader {
        return GlideImageLoader(glideRequestManager)
    }
}