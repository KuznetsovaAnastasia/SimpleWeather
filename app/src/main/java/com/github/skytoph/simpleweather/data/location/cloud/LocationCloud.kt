package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mappable
import com.google.gson.annotations.SerializedName

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
data class LocationCloud(
    private val name: String,
    private val namesLocal: Map<String, String?>,
    private val lat: Double,
    private val lng: Double,
) : Mappable<PlaceData, PlaceDataMapper> {

    override fun map(mapper: PlaceDataMapper): PlaceData = mapper.map(name, namesLocal, lat, lng)
}

data class LocationNamesJson(
    @SerializedName("name")
    val name: String,
    @SerializedName("local_names")
    val namesLocal: LocalNameJson,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lng: Double,
)

data class LocalNameJson(
    @SerializedName("en")
    val nameEn: String?,
    @SerializedName("uk")
    val nameUk: String?,
) {

    companion object {
        const val UK = "uk"
        const val EN = "en"
    }
}