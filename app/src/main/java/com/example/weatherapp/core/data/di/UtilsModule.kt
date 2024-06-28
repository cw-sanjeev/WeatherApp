package com.example.weatherapp.core.data.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.core.data.remote.NetworkHelperImpl
import com.example.weatherapp.core.data.utils.AppLogger
import com.example.weatherapp.core.data.utils.DefaultDispatcherprovider
import com.example.weatherapp.core.domain.remote.NetworkHelper
import com.example.weatherapp.core.domain.utils.DispatcherProvider
import com.example.weatherapp.core.domain.utils.Logger
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {
    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return AppLogger()
    }

    @Provides
    @Singleton
    fun provideFusedLocationClient(@ApplicationContext context: Context): FusedLocationProviderClient {
        return LocationServices.getFusedLocationProviderClient(context)
    }

    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    fun provideDispatcher(): DispatcherProvider {
        return DefaultDispatcherprovider()
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(connectivityManager: ConnectivityManager, @ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(connectivityManager, context)
    }
}