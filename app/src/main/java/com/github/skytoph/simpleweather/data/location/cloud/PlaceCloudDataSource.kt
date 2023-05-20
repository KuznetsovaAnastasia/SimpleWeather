package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceToCloudMapper
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface PlaceCloudDataSource {

    interface PlaceNameSearch {
        suspend fun placeName(placeId: String): String
    }

    interface PlaceSearch {
        suspend fun place(placeId: String): PlaceData
        suspend fun placeCoordinates(placeId: String): String
    }

    interface Search : PlaceNameSearch, PlaceSearch

    class Base @Inject constructor(
        private val findPlace: FindPlace,
        private val coordinatesDataSource: PlaceCoordinatesDataSource,
        private val nameDataSource: LocationNameDataSource,
        private val mapperData: PlaceDataMapper,
        private val mapper: PlaceToCloudMapper,
        private val idMapper: IdMapper,
    ) : PlaceCloudDataSource, Search {

        override suspend fun place(placeId: String): PlaceData =
            nameDataSource.find(idMapper.map(placeCoordinates(placeId))).map(mapperData)

        override suspend fun placeCoordinates(placeId: String): String =
            coordinatesDataSource.find(placeId).map(idMapper)

        override suspend fun placeName(placeId: String): String =
            mapper.mapToName(findPlace.find(placeId, Place.Field.ADDRESS_COMPONENTS))
    }
}