package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListDomainMapper
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryListUiMapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import javax.inject.Inject

interface SearchDetailsInteractor {
    suspend fun searchHistory(): List<SearchHistoryUi>
    suspend fun saveSearchResult(id: String, location: String)

    class Base @Inject constructor(
        private val locationsRepository: LocationsRepository,
        private val domainMapper: SearchHistoryListDomainMapper,
        private val uiMapper: SearchHistoryListUiMapper,
    ) : SearchDetailsInteractor {

        override suspend fun searchHistory(): List<SearchHistoryUi> =
            uiMapper.map(domainMapper.map(locationsRepository.searchHistory()))

        override suspend fun saveSearchResult(id: String, location: String) =
            locationsRepository.saveSearchLocation(id, location)
    }
}
