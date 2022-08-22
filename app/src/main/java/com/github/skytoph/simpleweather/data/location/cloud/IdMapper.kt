package com.github.skytoph.simpleweather.data.location.cloud

import javax.inject.Inject

interface IdMapper {
    fun map(id: String): Pair<Double, Double>
    fun map(lat: Double, lng: Double): String

    class Base @Inject constructor() : IdMapper {

        override fun map(id: String): Pair<Double, Double> {
            val coordinates = id.split(",").map { it.toDouble() }
            return Pair(coordinates[0], coordinates[1])
        }

        override fun map(lat: Double, lng: Double): String = "$lat,$lng"
    }
}