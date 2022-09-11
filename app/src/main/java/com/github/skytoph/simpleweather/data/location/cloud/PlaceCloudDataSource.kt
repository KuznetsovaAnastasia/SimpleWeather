package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject

interface LocationCloudDataSource {
    suspend fun place(placeId: String): PlaceCloud
    suspend fun placeCoordinates(placeId: String): String

    class Base @Inject constructor(
        private val client: PlacesClient,
        private val mapper: PlaceToCloudMapper,
        private val idMapper: IdMapper,
    ) : LocationCloudDataSource {

        override suspend fun place(placeId: String): PlaceCloud {
            val request = FetchPlaceRequest.newInstance(placeId,
                listOf(Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG))
            val response = client.fetchPlace(request).suspended()
            return mapper.map(response.place)
        }

        override suspend fun placeCoordinates(placeId: String): String {
            val request = FetchPlaceRequest.newInstance(placeId, listOf(Place.Field.LAT_LNG))
            val latLng = client.fetchPlace(request).suspended().place.latLng
            return idMapper.map(latLng?.latitude ?: 0.0, latLng?.longitude ?: 0.0)
        }
    }
}