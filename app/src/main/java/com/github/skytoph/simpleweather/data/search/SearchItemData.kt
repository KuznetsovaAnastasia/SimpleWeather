package com.github.skytoph.simpleweather.data.search

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemDataToUiMapper
import com.github.skytoph.simpleweather.presentation.search.model.SearchItemUi

sealed class SearchItemData : Mappable<SearchItemUi, SearchItemDataToUiMapper> {

    data class Location(
        private val id: String,
        private val title: String,
        private val subtitle: String,
    ) : SearchItemData() {
        override fun map(mapper: SearchItemDataToUiMapper): SearchItemUi =
            mapper.map(id, title, subtitle)
    }

    data class Fail(private val exception: Exception) : SearchItemData() {
        override fun map(mapper: SearchItemDataToUiMapper): SearchItemUi =
            mapper.map(exception.message.toString())
    }
}
