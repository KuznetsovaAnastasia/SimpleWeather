package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface LocationCloudDataSource {
    suspend fun place(placeId: String): PlaceCloud
    suspend fun placeName(placeId: String): String
    suspend fun placeCoordinates(placeId: String): String

    class Base @Inject constructor(
        private val findPlace: FindPlace,
        private val mapper: PlaceToCloudMapper,
        private val idMapper: IdMapper,
    ) : LocationCloudDataSource {

        override suspend fun place(placeId: String): PlaceCloud = mapper.map(findPlace.find(placeId,
            Place.Field.ADDRESS_COMPONENTS,
            Place.Field.LAT_LNG,
            Place.Field.ID))

        override suspend fun placeCoordinates(placeId: String): String {
            val latLng = findPlace.find(placeId, Place.Field.LAT_LNG).latLng
            return idMapper.map(latLng?.latitude ?: 0.0, latLng?.longitude ?: 0.0)
        }

        override suspend fun placeName(placeId: String): String =
            mapper.mapToName(findPlace.find(placeId, Place.Field.ADDRESS_COMPONENTS))
    }
}