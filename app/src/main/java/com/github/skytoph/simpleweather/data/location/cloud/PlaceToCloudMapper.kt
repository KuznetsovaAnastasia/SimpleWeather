package com.github.skytoph.simpleweather.data.location.cloud

import com.github.skytoph.simpleweather.core.Mapper
import com.google.android.libraries.places.api.model.Place

interface PlaceToCloudMapper : Mapper<PlaceCloud> {

    fun map(place: Place): PlaceCloud

    class Base : PlaceToCloudMapper {

        override fun map(place: Place) = PlaceCloud(place.id,
            place.addressComponents?.asList()
                ?.find { component -> component.types.contains("locality") }?.shortName ?: "",
            place.latLng.latitude,
            place.latLng.longitude)
    }
}