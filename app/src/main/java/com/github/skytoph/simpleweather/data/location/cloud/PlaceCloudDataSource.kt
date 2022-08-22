package com.github.skytoph.simpleweather.data.location.cloud

import android.util.Log
import com.github.skytoph.simpleweather.core.suspended
import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject

interface LocationCloudDataSource {
    suspend fun getPlace(id: String): PlaceCloud

    class Base @Inject constructor(
        private val client: PlacesClient,
        private val mapper: PlaceToCloudMapper,
    ) : LocationCloudDataSource {

        override suspend fun getPlace(id: String): PlaceCloud {
            val fields = listOf(Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)
            val request = FetchPlaceRequest.newInstance(id, fields)
            val response = client.fetchPlace(request).suspended()
            Log.e("ErrorTag", response.place.toString())
            return mapper.map(response.place)
        }
    }
}