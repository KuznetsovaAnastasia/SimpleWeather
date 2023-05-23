package com.github.skytoph.simpleweather.data.location.mapper

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import javax.inject.Inject

interface LocationDataMapper : Mapper<Map<String, String>> {

    fun map(location: List<Mappable<Pair<String, String>, LocalNameDataMapper>>): Map<String, String>

    class Base @Inject constructor(private val mapper: LocalNameDataMapper) : LocationDataMapper {

        override fun map(location: List<Mappable<Pair<String, String>, LocalNameDataMapper>>): Map<String, String> =
            location.associate { it.map(mapper) }
    }
}