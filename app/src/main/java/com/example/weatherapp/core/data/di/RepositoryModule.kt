package com.example.weatherapp.core.data.di

import android.app.Application
import android.content.Context
import com.example.weatherapp.core.data.repository.CoreRepositoryImpl
import com.example.weatherapp.core.domain.local.preferences.Preferences
import com.example.weatherapp.core.domain.repository.CoreRepository
import com.example.weatherapp.core.domain.usecase.GetLocation
import com.example.weatherapp.core.domain.usecase.LocationUseCases
import com.example.weatherapp.core.domain.usecase.SaveLocation
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCoreRepository(fusedLocationProviderClient: FusedLocationProviderClient, preferences: Preferences, @ApplicationContext context: Context): CoreRepository {
        return CoreRepositoryImpl(fusedLocationProviderClient, preferences, context as Application)
    }

    @Provides
    @Singleton
    fun provideLocationUseCases(repository: CoreRepository): LocationUseCases {
        return LocationUseCases(getLocation = GetLocation(repository), saveLocation = SaveLocation(repository))
    }
}