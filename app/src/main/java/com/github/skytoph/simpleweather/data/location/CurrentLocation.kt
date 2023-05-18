package com.github.skytoph.simpleweather.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.location.LocationManager
import androidx.annotation.RequiresPermission
import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.mapper.PlaceNameMapper
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton

interface CurrentLocation {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    suspend fun placeId(): String

    @Singleton
    class Base @Inject constructor(private val client: PlacesClient) : CurrentLocation,
        PlaceNameMapper.Abstract() {

        @SuppressLint("MissingPermission")
        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        override suspend fun placeId(): String {
            val request =
                FindCurrentPlaceRequest.newInstance(listOf(Place.Field.ID, Place.Field.LAT_LNG))
            val places =
                client.findCurrentPlace(request).suspended().placeLikelihoods.map { it.place }
            return (places.find { mapToName(it) != NAME_DEFAULT } ?: places.first()).id ?: ""
        }
    }

    @ActivityScoped
    class GPS @Inject constructor(private val locationManager: LocationManager) : CurrentLocation {

        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        override suspend fun placeId(): String {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            return if (location != null) {
                val latitude = location.latitude
                val longitude = location.longitude
                "$latitude$longitude"
            } else ""
        }
    }
}
