package com.github.skytoph.simpleweather.data.location

import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject

interface PlacesDataSource {
    suspend fun getId(placeId: String): String

    class Base @Inject constructor(
        private val client: PlacesClient,
        private val idMapper: IdMapper,
    ) : PlacesDataSource {

        override suspend fun getId(placeId: String): String {
            val request = FetchPlaceRequest.newInstance(placeId, listOf(Place.Field.LAT_LNG))
            val latLng = client.fetchPlace(request).suspended().place.latLng!!
            return idMapper.map(latLng.latitude, latLng.longitude)
        }
    }
}