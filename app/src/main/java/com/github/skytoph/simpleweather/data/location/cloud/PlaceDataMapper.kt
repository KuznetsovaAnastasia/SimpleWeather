package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mapper
import javax.inject.Inject

interface PlaceDataMapper : Mapper<PlaceData> {
    fun map(name: String, localNames: Map<String, String?>, lat: Double, lng: Double): PlaceData

    class Base @Inject constructor(private val idMapper: IdMapper) : PlaceDataMapper {

        override fun map(
            name: String, localNames: Map<String, String?>, lat: Double, lng: Double
        ): PlaceData {
            val names = localNames.toMutableMap().apply {
                put(KEY_NAME_DEFAULT, name)
                values.remove(null)
            }
            // TODO: replace name with map
            return PlaceData(idMapper.map(lat, lng), name, lat, lng)
        }

        companion object {
            const val KEY_NAME_DEFAULT = "default"
        }
    }
}