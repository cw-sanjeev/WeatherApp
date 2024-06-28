package com.example.weatherapp.core.data.di

import android.content.Context
import com.example.weatherapp.core.data.remote.RetrofitNetworkService
import com.example.weatherapp.core.data.utils.NetworkCacheInterceptor
import com.example.weatherapp.core.data.utils.OfflineCacheInterceptor
import com.example.weatherapp.core.domain.remote.NetworkHelper
import com.example.weatherapp.core.domain.remote.NetworkService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setPrettyPrinting().serializeNulls().create()
    }

    @Provides
    @Singleton
    fun provideOfflineCacheInterceptor(networkHelper: NetworkHelper): OfflineCacheInterceptor {
        return OfflineCacheInterceptor(networkHelper)
    }

    @Provides
    @Singleton
    fun provideNetworkCacheInterceptor(): NetworkCacheInterceptor {
        return NetworkCacheInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(@ApplicationContext context: Context, offlineCacheInterceptor: OfflineCacheInterceptor, networkCacheInterceptor: NetworkCacheInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(File(context.cacheDir, "http-cache"), 10L * 1024* 1024L))
            .addInterceptor(offlineCacheInterceptor)
            .addNetworkInterceptor(networkCacheInterceptor)
            .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.carwale.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkService(retrofit: Retrofit): NetworkService {
        return RetrofitNetworkService(retrofit)
    }
}