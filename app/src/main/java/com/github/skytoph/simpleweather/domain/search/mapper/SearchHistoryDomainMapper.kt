package com.github.skytoph.simpleweather.domain.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.search.model.SearchHistoryDomain
import javax.inject.Inject

interface SearchHistoryDomainMapper : Mapper<SearchHistoryDomain> {
    fun map(id: String, location: String): SearchHistoryDomain

    class Base @Inject constructor() : SearchHistoryDomainMapper {

        override fun map(id: String, location: String) = SearchHistoryDomain(id, location)
    }
}