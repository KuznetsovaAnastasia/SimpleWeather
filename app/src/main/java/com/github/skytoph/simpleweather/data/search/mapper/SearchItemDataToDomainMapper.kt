package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Logger
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.search.SearchItemDomain
import javax.inject.Inject

interface SearchItemDataToDomainMapper : Mapper<SearchItemDomain> {
    fun map(id: String, title: String, subtitle: String): SearchItemDomain
    fun map(exception: Exception): SearchItemDomain

    class Base @Inject constructor(private val logger: Logger) : SearchItemDataToDomainMapper {

        override fun map(id: String, title: String, subtitle: String) =
            SearchItemDomain.Location(id, title, subtitle)

        override fun map(exception: Exception) = SearchItemDomain.Fail(exception)
            .also { logger.log(SearchItemDataToDomainMapper::class.java.simpleName, exception) }
    }
}