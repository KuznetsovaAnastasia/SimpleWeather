package com.github.skytoph.simpleweather.data.mapper

import com.github.skytoph.simpleweather.core.util.SunCalculator
import com.github.skytoph.simpleweather.data.weather.WeatherData
import com.github.skytoph.simpleweather.data.location.model.LocationData
import com.github.skytoph.simpleweather.data.model.AirQualityData
import com.github.skytoph.simpleweather.domain.weather.model.WeatherDomain

interface WeatherDomainMapper {

    fun map(weatherData: WeatherData, airData: AirQualityData, locationData: LocationData): WeatherDomain

    class Base : WeatherDomainMapper {

        override fun map(
            weatherData: WeatherData,
            airData: AirQualityData,
            locationData: LocationData
        ): WeatherDomain {
            val mapper = WeatherDataToDomainMapper.Base(
                CurrentWeatherDataToDomainMapper.Base(locationData),
                IndicatorsDataToDomainMapper.Base(airData),
                HorizonDataToDomainMapper.Base(SunCalculator.Base()),
                WarningsDataToDomainMapper.Base(WarningDataToDomainMapper.Base())
            )
            return weatherData.map(mapper)
        }
    }
}