package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.exception.CanNotUpdateLocationException
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloud
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface PlaceToCloudMapper : Mapper<PlaceCloud> {

    fun map(place: Place): PlaceCloud
    fun mapToName(place: Place): String
    fun mapToName(exception: Exception): String

    class Base @Inject constructor() : PlaceToCloudMapper {

        override fun map(place: Place): PlaceCloud {
            return PlaceCloud(
                place.id!!,
                mapToName(place),
                place.latLng!!.latitude,
                place.latLng!!.longitude)
        }

        override fun mapToName(place: Place) = place.addressComponents?.asList()?.let {
            it.find { component -> component.types.contains("locality") }?.name
                ?: it.find { component -> component.types.contains("political") }?.name
                ?: it.firstOrNull()?.name
        } ?: "Unknown"

        override fun mapToName(exception: Exception): String = throw CanNotUpdateLocationException()
    }
}