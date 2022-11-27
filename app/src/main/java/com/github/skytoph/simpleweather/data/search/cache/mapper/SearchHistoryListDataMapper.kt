package com.github.skytoph.simpleweather.data.search.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryData
import com.github.skytoph.simpleweather.data.search.cache.model.SearchHistoryDB
import javax.inject.Inject

interface SearchHistoryListDataMapper : Mapper<List<SearchHistoryData>> {
    fun map(data: List<SearchHistoryDB>): List<SearchHistoryData>

    class Base @Inject constructor(private val mapper: SearchHistoryDataMapper) : SearchHistoryListDataMapper {

        override fun map(data: List<SearchHistoryDB>): List<SearchHistoryData> =
            data.map { it.map(mapper) }
    }
}