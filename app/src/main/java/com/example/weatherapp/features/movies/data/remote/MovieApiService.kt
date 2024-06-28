package com.example.weatherapp.features.movies.data.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface MovieApiService {

    fun searchMovie(@Url url: String)
}