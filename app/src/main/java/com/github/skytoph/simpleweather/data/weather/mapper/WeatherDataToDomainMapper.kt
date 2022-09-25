package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.model.*
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastListDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain
import javax.inject.Inject

interface WeatherDataToDomainMapper : Mapper<WeatherDomain> {

    fun map(
        id: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alertData: List<AlertData>,
        hourly: List<HourlyForecastData>
    ): WeatherDomain

    fun map(e: Exception): WeatherDomain

    class Base @Inject constructor(
        private val weatherMapper: CurrentWeatherDataToDomainMapper,
        private val indicatorsMapper: IndicatorsDataToDomainMapper,
        private val horizonMapper: HorizonDataToDomainMapper,
        private val warningsMapper: WarningsDataToDomainMapper,
        private val hourlyMapper: HourlyForecastListDomainMapper,
    ) : WeatherDataToDomainMapper, Mapper.ToDomain<WeatherDomain>() {

        override fun map(
            id: String,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alertData: List<AlertData>,
            hourly: List<HourlyForecastData>
        ): WeatherDomain = WeatherDomain.Base(
            id,
            currentWeatherData.map(weatherMapper),
            indicatorsData.map(indicatorsMapper),
            horizonData.map(horizonMapper),
            warningsMapper.map(alertData),
            hourlyMapper.map(hourly)
        )

        override fun map(e: Exception): WeatherDomain = WeatherDomain.Fail(errorType(e))

    }
}