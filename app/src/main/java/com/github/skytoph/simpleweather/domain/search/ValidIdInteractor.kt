package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import javax.inject.Inject

interface ValidIdInteractor {
    suspend fun isFavorite(id: String): Boolean
    suspend fun validId(placeId: String): String

    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Mutable,
        private val placeCloudDataSource: LocationCloudDataSource.PlaceSearch,
    ) : ValidIdInteractor {

        override suspend fun isFavorite(id: String): Boolean = weatherRepository.contains(id)

        override suspend fun validId(placeId: String): String {
            val validId = try {
                placeCloudDataSource.placeCoordinates(placeId)
            } catch (exception: Exception) {
                placeId
            }
            return if (isFavorite(validId)) validId else placeId
        }
    }
}
