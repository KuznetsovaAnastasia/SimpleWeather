package com.github.skytoph.simpleweather.domain.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.weather.mapper.ContentUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.CurrentWeatherDomainToUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HorizonDomainToUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.IndicatorsDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.CurrentWeatherUi
import com.github.skytoph.simpleweather.presentation.weather.HorizonUi
import com.github.skytoph.simpleweather.presentation.weather.IndicatorsUi
import com.github.skytoph.simpleweather.presentation.weather.WeatherUi

data class ContentDomain(
    private val currentWeather: CurrentWeatherDomain,
    private val indicators: IndicatorsDomain,
    private val horizon: HorizonDomain,
    private val forecast: ForecastDomain,
) : Mappable<WeatherUi, ContentUiMapper> {

    override fun map(mapper: ContentUiMapper): WeatherUi =
        mapper.map(currentWeather, indicators, horizon, forecast)
}

data class CurrentWeatherDomain(
    private val city: String,
    private val temperature: Double,
    private val weatherId: Int,
) : Mappable<CurrentWeatherUi, CurrentWeatherDomainToUiMapper> {

    override fun map(mapper: CurrentWeatherDomainToUiMapper): CurrentWeatherUi =
        mapper.map(city, temperature, weatherId)
}

data class IndicatorsDomain(
    private val time: Long,
    private val uvi: Double,
    private val precipitationProb: Double,
    private val airQualityIndex: Int,
) : Mappable<IndicatorsUi, IndicatorsDomainToUiMapper> {

    override fun map(mapper: IndicatorsDomainToUiMapper): IndicatorsUi =
        mapper.map(time, uvi, precipitationProb, airQualityIndex)
}

data class HorizonDomain(
    private val sunrise: Int,
    private val sunset: Int,
    private val dayLength: Int,
    private val daylight: Int,
    private val sunPosition: Double,
) : Mappable<HorizonUi, HorizonDomainToUiMapper> {

    override fun map(mapper: HorizonDomainToUiMapper): HorizonUi =
        mapper.map(sunrise, sunset, dayLength, daylight, sunPosition)
}