package com.github.skytoph.simpleweather.data.search

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemDataToDomainMapper
import com.github.skytoph.simpleweather.domain.search.SearchItemDomain

sealed class SearchItemData : Mappable<SearchItemDomain, SearchItemDataToDomainMapper> {

    data class Location(
        private val id: String,
        private val title: String,
        private val subtitle: String,
    ) : SearchItemData() {
        override fun map(mapper: SearchItemDataToDomainMapper): SearchItemDomain =
            mapper.map(id, title, subtitle)
    }

    data class Fail(private val exception: Exception) : SearchItemData() {
        override fun map(mapper: SearchItemDataToDomainMapper): SearchItemDomain =
            mapper.map(exception)
    }
}
