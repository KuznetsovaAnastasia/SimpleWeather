package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.model.*
import com.github.skytoph.simpleweather.domain.model.WeatherDomain

interface WeatherDataToDomainMapper : Mapper<WeatherDomain> {

    fun map(
        time: Long,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        sunData: SunData,
        alertData: List<AlertData>
    ): WeatherDomain

    fun map(e: Exception): WeatherDomain

    class Base(
//        private val airQuality: AirQualityData,
        private val weatherMapper: CurrentWeatherDataToDomainMapper,
        private val indicatorsMapper: IndicatorsDataToDomainMapper,
        private val horizonMapper: HorizonDataToDomainMapper,
        private val warningsMapper: WarningsDataToDomainMapper,
    ) : WeatherDataToDomainMapper {

        override fun map(
            time: Long,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            sunData: SunData,
            alertData: List<AlertData>
        ): WeatherDomain {
            return WeatherDomain.Base(
                currentWeatherData.map(weatherMapper),
//                indicatorsMapper.map(indicatorsData, airQuality),
                indicatorsData.map(indicatorsMapper),
                sunData.map(horizonMapper),
                warningsMapper.map(alertData)
            )
        }

        override fun map(e: Exception): WeatherDomain = WeatherDomain.Fail(e.message.toString())
    }
}