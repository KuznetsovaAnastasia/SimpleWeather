package com.github.skytoph.simpleweather.data.location

import android.Manifest
import android.annotation.SuppressLint
import androidx.annotation.RequiresPermission
import com.github.skytoph.simpleweather.core.suspended
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FindCurrentPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject
import javax.inject.Singleton

interface CurrentLocationId {
    @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    suspend fun placeId(): String

    @Singleton
    class Base @Inject constructor(
        private val client: PlacesClient,
        private val placeFilter: ChoosePlace
    ) : CurrentLocationId,
        ChoosePlace.Abstract() {

        @SuppressLint("MissingPermission")
        @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
        override suspend fun placeId(): String {
            val request = FindCurrentPlaceRequest.newInstance(listOf(Place.Field.ID))
            val places =
                client.findCurrentPlace(request).suspended().placeLikelihoods.map { it.place }
            return placeFilter.choosePlace(places)?.id ?: ""
        }
    }
}