package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import javax.inject.Inject

interface SearchResultsDomainToUiMapper : Mapper<List<SearchItemUi>> {
    fun map(list: List<SearchItemDomain>): List<SearchItemUi>

    class Base @Inject constructor(private val mapper: SearchItemDomainToUiMapper) :
        SearchResultsDomainToUiMapper {

        override fun map(list: List<SearchItemDomain>) = list.map { it.map(mapper) }
    }
}