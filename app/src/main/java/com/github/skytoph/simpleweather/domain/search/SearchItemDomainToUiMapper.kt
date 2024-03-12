package com.github.skytoph.simpleweather.domain.search

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi
import javax.inject.Inject

interface SearchItemDomainToUiMapper : Mapper<SearchItemUi>, MapAttribution {
    fun map(id: String, title: String, subtitle: String): SearchItemUi
    fun map(exception: Exception): SearchItemUi

    class Base @Inject constructor() :
        Mapper.UiAbstract<SearchItemUi>(), SearchItemDomainToUiMapper {

        override fun map(id: String, title: String, subtitle: String) =
            SearchItemUi.Location(id, title, subtitle)

        override fun map(exception: Exception) = SearchItemUi.Fail(messageRes(exception))

        override fun map(): SearchItemUi = SearchItemUi.Attribution
    }
}

interface MapAttribution {
    fun map(): SearchItemUi
}