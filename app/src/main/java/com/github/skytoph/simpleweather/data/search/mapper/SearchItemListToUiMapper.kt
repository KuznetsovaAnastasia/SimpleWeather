package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

interface SearchItemListToUiMapper : Mapper<List<SearchItemUi>> {
    fun map(list: List<SearchItemData>): List<SearchItemUi>

    class Base(private val mapper: SearchItemDataToUiMapper) : SearchItemListToUiMapper {

        override fun map(list: List<SearchItemData>) = list.map { it.map(mapper) }
    }
}