package com.github.skytoph.simpleweather.domain.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchHistoryUi
import javax.inject.Inject

interface SearchHistoryUiMapper : Mapper<SearchHistoryUi> {
    fun map(id: String, location: String): SearchHistoryUi

    class Base @Inject constructor() : SearchHistoryUiMapper {

        override fun map(id: String, location: String) = SearchHistoryUi(id, location)
    }
}