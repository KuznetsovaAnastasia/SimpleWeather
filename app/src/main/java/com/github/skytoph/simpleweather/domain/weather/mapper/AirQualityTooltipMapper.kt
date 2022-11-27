package com.github.skytoph.simpleweather.domain.weather.mapper

interface AirQualityTooltipMapper {
    fun aq(aq: Int): Int
}