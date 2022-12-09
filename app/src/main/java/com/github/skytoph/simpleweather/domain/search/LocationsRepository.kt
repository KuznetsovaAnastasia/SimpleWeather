package com.github.skytoph.simpleweather.domain.search

import androidx.annotation.RequiresPermission
import com.github.skytoph.simpleweather.data.location.CurrentLocation
import com.github.skytoph.simpleweather.data.location.cloud.LocationCloudDataSource
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryCache
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryData
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryDataMapper
import com.github.skytoph.simpleweather.data.search.cache.mapper.SearchHistoryListDataMapper
import javax.inject.Inject

interface LocationsRepository {
    suspend fun placeCoordinates(placeId: String): String
    suspend fun saveSearchLocation(placeId: String, location: String)
    suspend fun clearSearchHistory()
    suspend fun currentPlaceId(): String
    suspend fun searchHistory(): List<SearchHistoryData>

    class Base @Inject constructor(
        private val placeCloudDataSource: LocationCloudDataSource.PlaceSearch,
        private val searchHistoryCache: SearchHistoryCache,
        private val currentLocation: CurrentLocation,
        private val listMapper: SearchHistoryListDataMapper,
        private val mapper: SearchHistoryDataMapper,
    ) : LocationsRepository {

        override suspend fun placeCoordinates(placeId: String): String =
            placeCloudDataSource.placeCoordinates(placeId)

        override suspend fun searchHistory(): List<SearchHistoryData> =
            listMapper.map(searchHistoryCache.readAll())

        override suspend fun saveSearchLocation(placeId: String, location: String) =
            searchHistoryCache.saveOrUpdate(placeId, mapper.map(placeId, location))

        override suspend fun clearSearchHistory() =
            searchHistoryCache.clear()

        @RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
        override suspend fun currentPlaceId(): String = currentLocation.placeId()
    }
}
