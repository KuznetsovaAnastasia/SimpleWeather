package com.github.skytoph.simpleweather.data.location

import com.google.android.libraries.places.api.model.Place

interface ChoosePlace {
    fun <T> choosePlace(places: List<T>, predicate: (T?, Int) -> Boolean?): T?
    fun choosePlace(places: List<Place>): Place?

    abstract class Abstract : ChoosePlace {

        override fun choosePlace(places: List<Place>): Place? =
            choosePlace(places) { place, i -> place?.types?.contains(TARGET_TYPES[i]) }

        override fun <T> choosePlace(places: List<T>, predicate: (T?, Int) -> Boolean?): T? {
            var place: T? = null
            var i = 0
            while (place == null && i < TARGET_TYPES.size) {
                place = places.find { predicate(it, i) ?: false }
                i++
            }
            return place ?: places.firstOrNull()
        }

        protected companion object {
            val TARGET_TYPES = listOf(
                Place.Type.SUBLOCALITY,
                Place.Type.LOCALITY,
                Place.Type.ADMINISTRATIVE_AREA_LEVEL_2,
                Place.Type.ADMINISTRATIVE_AREA_LEVEL_1
            )
            val TARGET_SEARCH_TYPES = listOf(Place.Type.SUBLOCALITY, Place.Type.LOCALITY)
        }
    }
}