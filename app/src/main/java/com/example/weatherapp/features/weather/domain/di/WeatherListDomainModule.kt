package com.example.weatherapp.features.weather.domain.di

import com.example.weatherapp.features.weather.domain.repository.WeatherListRepository
import com.example.weatherapp.features.weather.domain.usecase.CityListUseCases
import com.example.weatherapp.features.weather.domain.usecase.GetCityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object WeatherListDomainModule {
    @Provides
    @ViewModelScoped
    fun provideCityListUseCases(repository: WeatherListRepository): CityListUseCases {
        return CityListUseCases(
            getCityUseCase = GetCityUseCase(repository)
        )
    }
}