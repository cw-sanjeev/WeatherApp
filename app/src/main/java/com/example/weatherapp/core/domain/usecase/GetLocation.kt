package com.example.weatherapp.core.domain.usecase

import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.CoreRepository

class GetLocation (private val coreRepository: CoreRepository) {
    suspend operator fun invoke(): Result<Location?> {
        return coreRepository.getLocation()
    }
}