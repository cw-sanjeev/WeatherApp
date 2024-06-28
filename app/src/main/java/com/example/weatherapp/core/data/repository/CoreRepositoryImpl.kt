package com.example.weatherapp.core.data.repository

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.weatherapp.core.domain.local.preferences.Preferences
import com.example.weatherapp.core.domain.model.Location
import com.example.weatherapp.core.domain.repository.CoreRepository
import com.example.weatherapp.core.domain.exceptions.PermissionNotFoundException
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resumeWithException

class CoreRepositoryImpl(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    private val preferences: Preferences,
    private val context: Context
) : CoreRepository {
    override suspend fun getLocation(): Result<Location?> {
        return try {
            val location = preferences.getLocation()
            if (location != null) {
                Result.success(location)
            } else {
                val location = getLastKnownLocation()
                Result.success(location)
            }
        } catch (exception: Exception) {
            Log.e(CoreRepositoryImpl::class.java.name, exception.message.toString())
            Result.failure(exception)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun getLastKnownLocation(): Location? {
        val hasLocationPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.GPS_PROVIDER
            )

        if (!hasLocationPermission || !isGpsEnabled) throw PermissionNotFoundException("Location Permission not found")

        return suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    if (location != null) {
                        cont.resume(Location(location.latitude, location.longitude), null)
                    } else {
                        cont.resume(null, null)
                    }
                }
                .addOnFailureListener { exception ->
                    cont.resumeWithException(exception)
                }
        }
    }

    override suspend fun saveLocation(lat: Double, long: Double) {
        preferences.saveLocation(Location(lat, long))
    }
}