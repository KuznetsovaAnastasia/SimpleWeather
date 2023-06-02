package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.github.skytoph.simpleweather.domain.search.AddAttribution
import com.github.skytoph.simpleweather.domain.search.SearchItemDomain
import javax.inject.Inject

interface SearchResultsDataToDomainMapper : Mapper<List<SearchItemDomain>> {
    fun map(list: List<SearchItemData>): List<SearchItemDomain>

    class Base @Inject constructor(
        private val mapper: SearchItemDataToDomainMapper,
        private val attributionMapper: AddAttribution
    ) : SearchResultsDataToDomainMapper {

        override fun map(list: List<SearchItemData>): List<SearchItemDomain> =
            list.map { it.map(mapper) }.let { attributionMapper.addIfValid(it) }
    }
}