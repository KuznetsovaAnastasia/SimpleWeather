package com.github.skytoph.simpleweather.data.location.cloud.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.data.location.cloud.PlaceData
import com.github.skytoph.simpleweather.data.location.mapper.PlaceDataMapper
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/*

[
   {
      "name":"London",
      "local_names":{
         "ms":"London"...
      },
      "lat":51.5073219,
      "lon":-0.1276474,
 */
@JsonClass(generateAdapter = true)
data class LocationCloud(
    val name: String,
    val namesLocal: Map<String, String?>,
    val lat: Double,
    val lng: Double,
) : Mappable<PlaceData, PlaceDataMapper> {

    override fun map(mapper: PlaceDataMapper): PlaceData =
        mapper.map(name, namesLocal, lat, lng)
}

@JsonClass(generateAdapter = true)
data class LocationNamesJson(
    @Json(name = "name")
    val name: String?,
    @Json(name = "local_names")
    val local_names: LocalNameJson?,
    @Json(name = "lat")
    val lat: Double,
    @Json(name = "lon")
    val lon: Double,
)

@JsonClass(generateAdapter = true)
data class LocalNameJson(
    @Json(name = "en")
    val en: String?,
    @Json(name = "uk")
    val uk: String?,
) {

    companion object {
        const val UK = "uk"
        const val EN = "en"
    }
}