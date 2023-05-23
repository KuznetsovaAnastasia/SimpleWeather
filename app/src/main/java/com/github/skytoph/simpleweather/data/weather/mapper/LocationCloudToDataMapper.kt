package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.data.location.mapper.LocalNameDataMapper
import java.util.*
import javax.inject.Inject

interface LocationCloudToDataMapper {
    fun map(location: Map<String, String?>, defaultName: String): Map<String, String>

    class Base @Inject constructor(private val mapper: LocalNameDataMapper) :
        LocationCloudToDataMapper {

        override fun map(location: Map<String, String?>, defaultName: String): Map<String, String> =
            location.mapNotNull { (key, value) -> value?.let { mapper.map(Locale(key), it) } }
                .toMutableList()
                .apply { add(mapper.map(name = defaultName)) }
                .toMap()
    }
}