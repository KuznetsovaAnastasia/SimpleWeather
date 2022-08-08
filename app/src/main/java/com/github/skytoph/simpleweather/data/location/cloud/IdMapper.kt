package com.github.skytoph.simpleweather.data.location.cloud

interface IdMapper {
    fun map(id: String): Pair<Double, Double>

    class Base : IdMapper {
        override fun map(id: String): Pair<Double, Double> {
            val coordinates = id.split(",").map { it.toDouble() }
            return Pair(coordinates[0], coordinates[1])
        }
    }
}
