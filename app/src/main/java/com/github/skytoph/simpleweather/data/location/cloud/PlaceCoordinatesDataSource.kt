package com.github.skytoph.simpleweather.data.location.cloud

import javax.inject.Inject

interface PlaceCoordinatesDataSource {
    suspend fun find(placeId: String): PlaceCoordinatesCloud

    class Base @Inject constructor(private val service: PlaceCoordinatesService) :
        PlaceCoordinatesDataSource {

        override suspend fun find(placeId: String) = service.getPlace(placeId).execute().body()!!
    }
}