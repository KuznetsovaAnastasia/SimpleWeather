package com.github.skytoph.simpleweather.domain.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.weather.ErrorType
import com.github.skytoph.simpleweather.domain.weather.mapper.*
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi
import com.github.skytoph.simpleweather.presentation.weather.WeatherUiComponent

sealed class WeatherDomain : Mappable<WeatherUi, WeatherDomainToUiMapper> {

    class Base(
        private val id: String,
        private val currentWeather: CurrentWeather,
        private val indicators: Indicators,
        private val horizon: Horizon,
        private val warnings: List<Warning>,
    ) : WeatherDomain() {

        override fun map(mapper: WeatherDomainToUiMapper): WeatherUi =
            mapper.map(id, currentWeather, indicators, horizon, warnings)
    }

    class Fail(private val errorType: ErrorType) : WeatherDomain() {

        // TODO: replace with error type
        override fun map(mapper: WeatherDomainToUiMapper): WeatherUi = mapper.map(errorType)
    }

    data class CurrentWeather(
        private val city: String,
        private val temperature: Double,
        private val weatherId: Int,
        private val favorite: Boolean,
    ) : Mappable<WeatherUiComponent.Current, CurrentWeatherDomainToUiMapper> {

        override fun map(mapper: CurrentWeatherDomainToUiMapper): WeatherUiComponent.Current =
            mapper.map(city, temperature, weatherId)
    }

    data class Indicators(
        private val time: Long,
        private val uvi: Double,
        private val precipitationProb: Double,
        private val airQualityIndex: Int,
    ) : Mappable<WeatherUiComponent.Indicator, IndicatorsDomainToUiMapper> {

        override fun map(mapper: IndicatorsDomainToUiMapper): WeatherUiComponent.Indicator =
            mapper.map(time, uvi, precipitationProb, airQualityIndex)
    }

    data class Horizon(
        private val sunrise: Long,
        private val sunset: Long,
        private val dayLength: Long,
        private val daylight: Long,
        private val sunPosition: Double,
    ) : Mappable<WeatherUiComponent.Horizon, HorizonDomainToUiMapper> {

        override fun map(mapper: HorizonDomainToUiMapper): WeatherUiComponent.Horizon =
            mapper.map(sunrise, sunset, dayLength, daylight, sunPosition)
    }

    data class Warning(
        private val event: String,
        private val startTime: Long,
        private val precipitationProb: Double,
    ) : Mappable<WeatherUiComponent.Warning, WarningDomainToUiMapper> {

        override fun map(mapper: WarningDomainToUiMapper): WeatherUiComponent.Warning =
            mapper.map(event, startTime, precipitationProb)
    }
}
