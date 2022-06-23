package com.github.skytoph.simpleweather.data

import com.github.skytoph.simpleweather.core.MappableToDomain
import com.github.skytoph.simpleweather.data.mapper.WeatherDataToDomainMapper
import com.github.skytoph.simpleweather.data.model.*
import com.github.skytoph.simpleweather.domain.model.WeatherDomain
import java.lang.Exception

sealed class WeatherData : MappableToDomain<WeatherDomain, WeatherDataToDomainMapper> {

    data class Base(
        private val time: Long,
        private val currentWeatherData: CurrentWeatherData,
        private val indicatorsData: IndicatorsData,
        private val sunData: SunData,
        private val alertData: List<AlertData>,
    ) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain =
            mapper.map(time, currentWeatherData, indicatorsData, sunData, alertData)
    }

    data class Fail(private val exception: Exception) : WeatherData() {

        override fun map(mapper: WeatherDataToDomainMapper): WeatherDomain = mapper.map(exception)
    }
}