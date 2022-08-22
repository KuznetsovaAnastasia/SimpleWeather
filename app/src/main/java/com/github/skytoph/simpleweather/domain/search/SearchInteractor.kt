package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.data.search.SearchLocationDataSource
import com.github.skytoph.simpleweather.domain.weather.WeatherRepository
import javax.inject.Inject

interface SearchInteractor {
    fun startSession()
    suspend fun search(query: String, showResult: (List<SearchItemDomain>) -> Unit)
    suspend fun refreshCache()

    class Base @Inject constructor(
        private val searchDataSource: SearchLocationDataSource,
        private val weatherRepository: WeatherRepository.Save,
    ) : SearchInteractor {

        override fun startSession() = searchDataSource.startSession()

        override suspend fun search(query: String, showResult: (List<SearchItemDomain>) -> Unit) =
            searchDataSource.getPredictions(query, showResult)

        override suspend fun refreshCache() = weatherRepository.refreshAll()
    }
}