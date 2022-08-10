package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import javax.inject.Inject

interface SearchResultsToUiMapper : Mapper<List<SearchItemUi>> {
    fun map(list: List<SearchItemData>): List<SearchItemUi>

    class Base @Inject constructor(private val mapper: SearchItemDataToUiMapper) :
        SearchResultsToUiMapper {

        override fun map(list: List<SearchItemData>) = list.map { it.map(mapper) }
    }
}