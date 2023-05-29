package com.github.skytoph.simpleweather.data.location

import android.Manifest
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import javax.inject.Inject

interface CurrentLocationCoordinates {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    suspend fun placeCoordinates(): Pair<Double, Double>

    class GPS @Inject constructor(
        private val locationManager: LocationManager,
        private val idMapper: IdMapper,
    ) : CurrentLocationCoordinates {

        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        override suspend fun placeCoordinates(): Pair<Double, Double> =
            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)?.let { location ->
                idMapper.mapToCoordinates(location.latitude, location.longitude)
            } ?: (0.0 to 0.0)
    }
}