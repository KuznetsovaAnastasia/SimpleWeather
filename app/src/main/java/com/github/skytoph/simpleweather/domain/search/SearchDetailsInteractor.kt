package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListDomainMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListUiMapper
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import javax.inject.Inject

interface SearchDetailsInteractor {
    suspend fun isFavorite(id: String): Boolean
    suspend fun validId(placeId: String): String
    suspend fun searchHistory(): List<SearchHistoryUi>
    suspend fun saveSearchResult(id: String, location: String)

    class Base @Inject constructor(
        private val weatherRepository: WeatherRepository.Contains,
        private val locationsRepository: LocationsRepository,
        private val domainMapper: SearchHistoryListDomainMapper,
        private val uiMapper: SearchHistoryListUiMapper,
    ) : SearchDetailsInteractor {

        override suspend fun isFavorite(id: String): Boolean = weatherRepository.contains(id)

        override suspend fun validId(placeId: String): String {
            val validId = try {
                locationsRepository.placeCoordinates(placeId)
            } catch (exception: Exception) {
                placeId
            }
            return if (isFavorite(validId)) validId else placeId
        }

        override suspend fun searchHistory(): List<SearchHistoryUi> =
            uiMapper.map(domainMapper.map(locationsRepository.searchHistory()))

        override suspend fun saveSearchResult(id: String, location: String) =
            locationsRepository.saveSearchLocation(id, location)
    }
}
