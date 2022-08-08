package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.*
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

interface WeatherDataToDomainMapper : Mapper<WeatherDomain> {

    fun map(
        locationData: LocationData,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alertData: List<AlertData>,
    ): WeatherDomain

    fun map(e: Exception): WeatherDomain

    class Base(
        private val weatherMapper: CurrentWeatherDomainMapper,
        private val indicatorsMapper: IndicatorsDataToDomainMapper,
        private val horizonMapper: HorizonDataToDomainMapper,
        private val warningsMapper: WarningsDataToDomainMapper,
    ) : WeatherDataToDomainMapper {

        override fun map(
            locationData: LocationData,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alertData: List<AlertData>,
        ): WeatherDomain = WeatherDomain.Base(
            locationData.map(),
            weatherMapper.map(currentWeatherData, locationData),
            indicatorsData.map(indicatorsMapper),
            horizonData.map(horizonMapper),
            warningsMapper.map(alertData)
        )

        override fun map(e: Exception): WeatherDomain = WeatherDomain.Fail(e.message.toString())
    }
}