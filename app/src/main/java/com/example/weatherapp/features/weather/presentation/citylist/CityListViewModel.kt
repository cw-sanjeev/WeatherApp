package com.example.weatherapp.features.weather.presentation.citylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.R
import com.example.weatherapp.core.domain.base.UiError
import com.example.weatherapp.core.domain.base.UiText
import com.example.weatherapp.core.domain.exceptions.NoInternetException
import com.example.weatherapp.core.domain.exceptions.PermissionNotFoundException
import com.example.weatherapp.core.domain.usecase.LocationUseCases
import com.example.weatherapp.core.domain.usecase.ObserveNetworkStatusUseCase
import com.example.weatherapp.core.domain.utils.DispatcherProvider
import com.example.weatherapp.core.domain.utils.Logger
import com.example.weatherapp.features.weather.domain.usecase.CityListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityListViewModel @Inject constructor(
    private val locationUseCases: LocationUseCases,
    private val logger: Logger,
    private val cityListUseCases: CityListUseCases,
    private val dispatcherProvider: DispatcherProvider,
    private val observeNetworkStatusUseCase: ObserveNetworkStatusUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<CityListState> = MutableStateFlow(CityListState())
    val uiState: StateFlow<CityListState> = _uiState.asStateFlow()
    var cityListJob: Job? = null
    var locationJob: Job? = null

    init {
        getCityList()
    }

    fun getCityList() {
        if (cityListJob?.isActive == true) return
        cityListJob = viewModelScope.launch(dispatcherProvider.io) {
            val cityListResult = cityListUseCases.getCityUseCase()
            cityListResult.onSuccess {
                _uiState.value.copy(
                    cities = cityListResult.getOrNull(), isLoading = false
                )
            }
            cityListResult.onFailure { exception ->
                when (exception) {
                    is NoInternetException -> {
                        logger.d(CityListViewModel::class.java.toString(), "No Internet Connection")
                        _uiState.value.copy(
                            isLoading = false,
                            uiError = UiError.NoInternet("No Internet Connection")
                        )
                    }

                    else -> {
                        logger.d(
                            CityListViewModel::class.java.toString(), exception.message.toString()
                        )
                        _uiState.value.copy(
                            isLoading = false,
                            uiError = UiError.SomethingWentWrong("Something Went Wrong")
                        )
                    }
                }
            }
        }
    }

    fun getLocation() {
        if (locationJob?.isActive == true) return
        locationJob = viewModelScope.launch(dispatcherProvider.default) {
            val locationResult = locationUseCases.getLocation()
            locationResult.onSuccess {
                _uiState.value.copy(
                    location = locationResult.getOrNull(), isLoading = false
                )
            }
            locationResult.onFailure { exception ->
                when (exception) {
                    is PermissionNotFoundException -> {
                        logger.d(
                            CityListViewModel::class.java.toString(),
                            "Location Permission Not Given"
                        )
                        _uiState.value.copy(
                            useMessage = UiText.StringResource(R.string.location_permission_not_given)
                        )
                    }

                    else -> {
                        logger.d(
                            CityListViewModel::class.java.toString(), exception.message.toString()
                        )
                        _uiState.value.copy(
                            useMessage = UiText.StringResource(R.string.error_getting_location)
                        )
                    }
                }
            }
        }
    }

    fun userMessageShown() {
        _uiState.value.copy(
            useMessage = null
        )
    }
}