package com.github.skytoph.simpleweather.data.weather.cache.mapper.content.current

import com.github.skytoph.simpleweather.core.Mapper

interface CurrentDBMapper : Mapper<Unit> {

    fun map(location: String)
}
