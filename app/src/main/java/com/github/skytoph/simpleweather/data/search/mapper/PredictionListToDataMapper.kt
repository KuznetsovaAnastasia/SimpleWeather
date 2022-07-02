package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.google.android.libraries.places.api.model.AutocompletePrediction
import java.lang.Exception

interface PredictionListToDataMapper : Mapper<List<SearchItemData>> {
    fun map(list: List<AutocompletePrediction>): List<SearchItemData>
    fun map(exception: Exception): List<SearchItemData>

    class Base(private val mapper: PredictionToDataMapper) : PredictionListToDataMapper {

        override fun map(list: List<AutocompletePrediction>): List<SearchItemData> =
            list.map { mapper.map(it) }

        override fun map(exception: Exception) = listOf(SearchItemData.Fail(exception))
    }
}