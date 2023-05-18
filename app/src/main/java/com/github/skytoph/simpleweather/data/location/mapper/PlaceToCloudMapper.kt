package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceData
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface PlaceToCloudMapper : Mapper<PlaceData>, PlaceNameMapper {
    fun map(place: Place): PlaceData

    class Base @Inject constructor() : PlaceToCloudMapper, PlaceNameMapper.Abstract() {

        override fun map(place: Place): PlaceData {
            return PlaceData(
                place.id!!,
                mapToName(place),
                place.latLng!!.latitude,
                place.latLng!!.longitude
            )
        }
    }
}