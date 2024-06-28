package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.domain.repository.CoreRepository

class SaveLocation(private val coreRepository: CoreRepository) {
    suspend operator fun invoke(lat: Double, long: Double) {
        coreRepository.saveLocation(lat, long)
    }
}