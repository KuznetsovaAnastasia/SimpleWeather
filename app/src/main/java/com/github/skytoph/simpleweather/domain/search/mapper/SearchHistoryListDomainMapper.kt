package com.github.skytoph.simpleweather.domain.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.cache.SearchHistoryData
import com.github.skytoph.simpleweather.domain.search.model.SearchHistoryDomain
import javax.inject.Inject

interface SearchHistoryListDomainMapper : Mapper<List<SearchHistoryDomain>> {
    fun map(data: List<SearchHistoryData>): List<SearchHistoryDomain>

    class Base @Inject constructor(private val mapper: SearchHistoryDomainMapper) :
        SearchHistoryListDomainMapper {

        override fun map(data: List<SearchHistoryData>): List<SearchHistoryDomain> =
            data.map { it.map(mapper) }
    }
}