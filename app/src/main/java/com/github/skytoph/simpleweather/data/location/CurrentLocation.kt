package com.github.skytoph.simpleweather.data.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.mapper.PlaceNameMapper
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject
import javax.inject.Singleton

interface CurrentLocation {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    suspend fun placeId(): String

    @Singleton
    class Base @Inject constructor(private val client: PlacesClient) : CurrentLocation, PlaceNameMapper.Abstract() {

        @SuppressLint("MissingPermission")
        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        override suspend fun placeId(): String {
            val request = FindCurrentPlaceRequest.newInstance(listOf(Place.Field.ID))
            val places =
                client.findCurrentPlace(request).suspended().placeLikelihoods.map { it.place }
            return (places.find { mapToName(it) != NAME_DEFAULT } ?: places.first()).id ?: ""
        }
    }
}
