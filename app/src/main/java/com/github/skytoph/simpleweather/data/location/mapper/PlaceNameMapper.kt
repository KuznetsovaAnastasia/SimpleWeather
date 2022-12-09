package com.github.skytoph.simpleweather.data.location.mapper

import com.google.android.libraries.places.api.model.Place

interface PlaceNameMapper {
    fun mapToName(place: Place): String

    abstract class Abstract : PlaceNameMapper {
        override fun mapToName(place: Place) = place.addressComponents?.asList()?.let {
            it.find { component -> component.types.contains("locality") }?.name
                ?: it.find { component -> component.types.contains("political") }?.name
                ?: it.firstOrNull()?.name
        } ?: NAME_DEFAULT

        protected companion object {
            const val NAME_DEFAULT = "Unknown"
        }
    }
}