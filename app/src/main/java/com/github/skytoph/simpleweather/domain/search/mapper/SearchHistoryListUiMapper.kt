package com.github.skytoph.simpleweather.domain.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.search.model.SearchHistoryDomain
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import javax.inject.Inject

interface SearchHistoryListUiMapper : Mapper<SearchHistoryUi> {
    fun map(data: List<SearchHistoryDomain>): List<SearchHistoryUi>

    class Base @Inject constructor(private val mapper: SearchHistoryUiMapper) :
        SearchHistoryListUiMapper {

        override fun map(data: List<SearchHistoryDomain>): List<SearchHistoryUi> =
            data.map { it.map(mapper) }
    }
}