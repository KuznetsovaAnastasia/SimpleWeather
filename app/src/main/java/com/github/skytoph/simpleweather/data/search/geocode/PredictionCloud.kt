package com.github.skytoph.simpleweather.data.search.geocode

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.search.SearchItemData
import com.github.skytoph.simpleweather.data.search.mapper.SearchItemCloudToDataMapper
import com.squareup.moshi.Json

data class PredictionCloud(
    @Json(name = "name")
    private val name: String?,
    @Json(name = "lat")
    private val lat: Double,
    @Json(name = "lon")
    private val lon: Double,
    @Json(name = "country")
    private val country: String?,
    @Json(name = "state")
    private val state: String?,
) : Mappable<SearchItemData, SearchItemCloudToDataMapper> {

    override fun map(mapper: SearchItemCloudToDataMapper): SearchItemData =
        mapper.map("$lat,$lon", name.toString(), "$country, $state")
}
