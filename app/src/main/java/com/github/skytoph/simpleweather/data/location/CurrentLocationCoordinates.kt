package com.github.skytoph.simpleweather.data.location

import android.Manifest
import android.location.Location
import androidx.annotation.RequiresPermission
import com.github.skytoph.simpleweather.core.exception.FindCurrentLocationException
import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import javax.inject.Inject

interface CurrentLocationCoordinates {
    suspend fun placeCoordinates(): Pair<Double, Double>

    class GPS @Inject constructor(
        private val client: FusedLocationProviderClient,
        private val idMapper: IdMapper
    ) : CurrentLocationCoordinates {

        @RequiresPermission(anyOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
        override suspend fun placeCoordinates(): Pair<Double, Double> =
            client.lastLocation.suspended()?.map()
                ?: client.getCurrentLocation(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY, null)
                    .suspended()?.map()
                ?: throw FindCurrentLocationException()

        private fun Location.map(): Pair<Double, Double> =
            idMapper.mapToCoordinates(this.latitude, this.longitude)
    }
}