package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudToDataMapper
import javax.inject.Inject

interface PlaceCloudDataSource {
    suspend fun place(placeId: String): PlaceData
    suspend fun placeCoordinates(placeId: String): String

    class Base @Inject constructor(
        private val coordinatesDataSource: PlaceCoordinatesDataSource,
        private val nameDataSource: LocationNameDataSource,
        private val mapperData: PlaceCloudToDataMapper,
        private val idMapper: IdMapper,
    ) : PlaceCloudDataSource {

        override suspend fun place(placeId: String): PlaceData {
            val coordinates = coordinatesDataSource.find(placeId).mapToCoordinates(idMapper)
            val locationCloud = nameDataSource.find(coordinates)
            return mapperData.map(locationCloud, placeId)
        }

        override suspend fun placeCoordinates(placeId: String): String =
            coordinatesDataSource.find(placeId).map(idMapper)
    }
}