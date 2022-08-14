package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.github.skytoph.simpleweather.data.search.geocode.PredictionCloud
import javax.inject.Inject

interface SearchResultsCloudToDataMapper : Mapper<List<SearchItemData>> {
    fun map(results: List<PredictionCloud>): List<SearchItemData>

    class Base @Inject constructor(private val mapper: SearchItemCloudToDataMapper) :
        SearchResultsCloudToDataMapper {

        override fun map(results: List<PredictionCloud>): List<SearchItemData> =
            results.map { it.map(mapper) }
    }
}