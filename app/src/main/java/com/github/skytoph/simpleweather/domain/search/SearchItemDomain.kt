package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.core.ErrorType
import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

sealed class SearchItemDomain : Mappable<SearchItemUi, SearchItemDomainToUiMapper> {

    data class Location(
        private val id: String,
        private val title: String,
        private val subtitle: String,
    ) : SearchItemDomain() {
        override fun map(mapper: SearchItemDomainToUiMapper): SearchItemUi =
            mapper.map(id, title, subtitle)
    }

    data class Fail(private val errorType: ErrorType) : SearchItemDomain() {
        override fun map(mapper: SearchItemDomainToUiMapper): SearchItemUi =
            mapper.map(errorType)
    }

    object Attribution : SearchItemDomain() {
        override fun map(mapper: SearchItemDomainToUiMapper): SearchItemUi = mapper.map()
    }
}