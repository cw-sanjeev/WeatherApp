package com.example.weatherapp.core.domain.di

import com.example.weatherapp.core.domain.remote.NetworkHelper
import com.example.weatherapp.core.domain.usecase.ObserveNetworkStatusUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreDomainModule {
    @Provides
    @Singleton
    fun provideObserveNetworkUseCase(networkHelper: NetworkHelper): ObserveNetworkStatusUseCase {
        return ObserveNetworkStatusUseCase(networkHelper)
    }
}