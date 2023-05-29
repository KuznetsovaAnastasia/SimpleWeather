package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudToDataMapper
import javax.inject.Inject

interface PlaceCloudDataSource {
    suspend fun place(placeId: String): PlaceData
    suspend fun place(coordinates: Pair<Double, Double>): PlaceData
    suspend fun place(placeId: String, coordinates: Pair<Double, Double>): PlaceData
    suspend fun placeCoordinates(placeId: String): String

    class Base @Inject constructor(
        private val coordinatesDataSource: PlaceCoordinatesDataSource,
        private val nameDataSource: LocationNameDataSource,
        private val mapperData: PlaceCloudToDataMapper,
        private val idMapper: IdMapper,
    ) : PlaceCloudDataSource {

        override suspend fun place(placeId: String): PlaceData =
            place(placeId, coordinatesDataSource.find(placeId).mapToCoordinates(idMapper))

        override suspend fun place(coordinates: Pair<Double, Double>): PlaceData =
            place(coordinatesDataSource.find(coordinates).map(), coordinates)

        override suspend fun place(placeId: String, coordinates: Pair<Double, Double>): PlaceData =
            mapperData.map(nameDataSource.find(coordinates), placeId)

        override suspend fun placeCoordinates(placeId: String): String =
            coordinatesDataSource.find(placeId).map(idMapper)
    }
}