package com.github.skytoph.simpleweather.domain.search.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.search.mapper.SearchHistoryUiMapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi

data class SearchHistoryDomain(private val id: String, private val location: String) :
    Mappable<SearchHistoryUi, SearchHistoryUiMapper> {

    override fun map(mapper: SearchHistoryUiMapper): SearchHistoryUi = mapper.map(id, location)
}
