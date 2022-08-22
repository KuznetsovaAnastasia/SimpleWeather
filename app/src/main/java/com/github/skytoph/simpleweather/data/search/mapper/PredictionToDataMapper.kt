package com.github.skytoph.simpleweather.data.search.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.google.android.libraries.places.api.model.AutocompletePrediction
import javax.inject.Inject

interface PredictionToDataMapper : Mapper<SearchItemData> {
    fun map(prediction: AutocompletePrediction): SearchItemData

    class Base @Inject constructor() : PredictionToDataMapper {
        override fun map(prediction: AutocompletePrediction) =
            SearchItemData.Location(prediction.placeId,
                prediction.getPrimaryText(null).toString(),
                prediction.getSecondaryText(null).toString())
    }
}