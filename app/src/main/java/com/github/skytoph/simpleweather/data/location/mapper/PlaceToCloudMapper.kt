package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloud
import com.google.android.libraries.places.api.model.AddressComponent
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

interface PlaceToCloudMapper : Mapper<PlaceCloud> {

    fun map(place: Place): PlaceCloud

    class Base @Inject constructor() : PlaceToCloudMapper {

        override fun map(place: Place): PlaceCloud {
            return PlaceCloud(
                place.addressComponents?.asList()?.findPlaceName() ?: "Unknown",
                place.latLng!!.latitude,
                place.latLng!!.longitude)
        }

        private fun List<AddressComponent>.findPlaceName() =
            this.find { component -> component.types.contains("locality") }?.name
                ?: this.find { component -> component.types.contains("political") }?.name
                ?: this.firstOrNull()?.name
                ?: "Unknown"
    }
}