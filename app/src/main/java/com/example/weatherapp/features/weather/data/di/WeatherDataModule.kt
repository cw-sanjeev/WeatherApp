package com.example.weatherapp.features.weather.data.di

import com.example.weatherapp.core.domain.remote.NetworkService
import com.example.weatherapp.features.weather.data.remote.WeatherApiService
import com.example.weatherapp.features.weather.data.repository.WeatherDetailsRepositoryImpl
import com.example.weatherapp.features.weather.data.repository.WeatherListRepositoryImpl
import com.example.weatherapp.features.weather.domain.repository.WeatherDetailRepository
import com.example.weatherapp.features.weather.domain.repository.WeatherListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WeatherDataModule {
    @Provides
    @ViewModelScoped
    fun provideWeatherApiService(networkService: NetworkService): WeatherApiService =
        networkService.create(WeatherApiService::class.java)

    @Provides
    @ViewModelScoped
    fun provideWeatherListRepository(weatherApiService: WeatherApiService): WeatherListRepository {
        return WeatherListRepositoryImpl(weatherApiService)
    }

    @Provides
    @ViewModelScoped
    fun provideWeatherDetailsRepository(weatherApiService: WeatherApiService): WeatherDetailRepository {
        return WeatherDetailsRepositoryImpl(weatherApiService)
    }
}