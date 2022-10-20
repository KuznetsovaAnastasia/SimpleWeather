package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.exception.CanNotUpdateLocationException
import com.github.skytoph.simpleweather.core.suspended
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import javax.inject.Inject

interface FindPlace {
    suspend fun find(placeId: String, vararg fields: Place.Field): Place

    class Base @Inject constructor(private val client: PlacesClient) : FindPlace {

        override suspend fun find(placeId: String, vararg fields: Place.Field): Place =try {
            client.fetchPlace(FetchPlaceRequest.newInstance(placeId, fields.asList()))
                .suspended().place
        } catch (exception: Exception){
            throw CanNotUpdateLocationException()
        }
    }
}