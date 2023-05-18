package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface PlaceCloudDataSource {

    interface PlaceNameSearch {
        suspend fun placeName(placeId: String): String
    }

    interface PlaceSearch : PlaceNameSearch {
        suspend fun place(placeId: String): PlaceData
        suspend fun placeCoordinates(placeId: String): String
    }

    class Base @Inject constructor(
        private val findPlace: FindPlace,
        private val coordinatesDataSource: PlaceCoordinatesDataSource,
        private val mapper: PlaceToCloudMapper,
        private val idMapper: IdMapper,
    ) : PlaceCloudDataSource, PlaceSearch {

        override suspend fun place(placeId: String): PlaceData = mapper.map(
            findPlace.find(
                placeId,
                Place.Field.ADDRESS_COMPONENTS,
                Place.Field.LAT_LNG,
                Place.Field.ID
            )
        )

        override suspend fun placeCoordinates(placeId: String): String =
            coordinatesDataSource.find(placeId).map(idMapper)

        override suspend fun placeName(placeId: String): String =
            mapper.mapToName(findPlace.find(placeId, Place.Field.ADDRESS_COMPONENTS))
    }
}