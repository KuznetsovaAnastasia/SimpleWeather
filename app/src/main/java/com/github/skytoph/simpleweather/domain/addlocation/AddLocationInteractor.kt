package com.github.skytoph.simpleweather.domain.addlocation

import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

interface AddLocationInteractor {
    suspend fun save()
    suspend fun isFavorite(id: String): Boolean
    suspend fun translateId(placeId: String): String

    @ViewModelScoped
    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Mutable,
        private val placeCloudDataSource: LocationCloudDataSource,
    ) : AddLocationInteractor {

        override suspend fun translateId(placeId: String): String =
            placeCloudDataSource.placeCoordinates(placeId)

        override suspend fun isFavorite(id: String): Boolean = weatherRepository.contains(id)

        override suspend fun save() = weatherRepository.saveWeather()
    }
}
