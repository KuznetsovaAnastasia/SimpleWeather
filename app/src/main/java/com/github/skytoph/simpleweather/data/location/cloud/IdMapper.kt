package com.github.skytoph.simpleweather.data.location.cloud

import javax.inject.Inject

interface IdMapper {
    fun map(id: String): Pair<Double, Double>
    fun map(lat: Double, lng: Double): String
    fun mapToCoordinates(lat: Double, lng: Double): Pair<Double, Double>

    class Base @Inject constructor() : IdMapper {

        override fun map(id: String): Pair<Double, Double> {
            val coordinates = id.split(",").map { it.toDouble() }
            return mapToCoordinates(coordinates[0], coordinates[1])
        }

        override fun map(lat: Double, lng: Double): String = "$lat,$lng"

        override fun mapToCoordinates(lat: Double, lng: Double) = Pair(lat, lng)
    }

    interface MappableToStringId {
        fun map(mapper: IdMapper): String
    }

    interface MappableToCoordinates {
        fun mapToCoordinates(mapper: IdMapper): Pair<Double, Double>
    }

    interface Mappable : MappableToStringId, MappableToCoordinates
}