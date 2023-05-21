package com.github.skytoph.simpleweather.data.location.cloud

import javax.inject.Inject

interface PlaceCloudDataSource {
    suspend fun place(placeId: String): PlaceData
    suspend fun placeCoordinates(placeId: String): String

    class Base @Inject constructor(
        private val coordinatesDataSource: PlaceCoordinatesDataSource,
        private val nameDataSource: LocationNameDataSource,
        private val mapperData: PlaceDataMapper,
        private val idMapper: IdMapper,
    ) : PlaceCloudDataSource {

        override suspend fun place(placeId: String): PlaceData =
            nameDataSource.find(idMapper.map(placeCoordinates(placeId))).map(mapperData)

        override suspend fun placeCoordinates(placeId: String): String =
            coordinatesDataSource.find(placeId).map(idMapper)
    }
}