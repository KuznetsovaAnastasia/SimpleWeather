package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper

interface TimeDomainMapper: Mapper<Long> {
    fun map(time: Long): Long
}