package com.example.weatherapp.core.data.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.weatherapp.core.data.local.preferences.DefaultPreferences
import com.example.weatherapp.core.domain.local.preferences.Preferences
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Preferences.SHARED_PREF, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences, gson: Gson): Preferences {
        return DefaultPreferences(sharedPreferences, gson)
    }
}