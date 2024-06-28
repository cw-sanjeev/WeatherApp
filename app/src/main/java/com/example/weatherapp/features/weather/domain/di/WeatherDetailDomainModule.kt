package com.example.weatherapp.features.weather.domain.di

import com.example.weatherapp.features.weather.domain.repository.WeatherDetailRepository
import com.example.weatherapp.features.weather.domain.usecase.GetWeatherDetailUseCase
import com.example.weatherapp.features.weather.domain.usecase.WeatherDetailForCityUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WeatherDetailDomainModule {
    @Provides
    @ViewModelScoped
    fun provideWeatherDetailForCityUseCases(repository: WeatherDetailRepository): WeatherDetailForCityUseCases {
        return WeatherDetailForCityUseCases(
            getWeatherDetailUseCase = GetWeatherDetailUseCase(repository)
        )
    }
}