package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.CurrentDB
import javax.inject.Inject

interface CurrentDBMapper : Mapper<CurrentDB> {

    fun map(weatherId: Int, temperature: Double, location: String): CurrentDB

    class Base @Inject constructor() : CurrentDBMapper {

        override fun map(weatherId: Int, temperature: Double, location: String) = CurrentDB().apply {
            this.weatherCode = weatherId
            this.temperature = temperature
            this.location = location
        }
    }
}
