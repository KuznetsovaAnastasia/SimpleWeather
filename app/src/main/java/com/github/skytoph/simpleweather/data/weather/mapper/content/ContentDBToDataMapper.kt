package com.github.skytoph.simpleweather.data.weather.mapper.content

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.cache.model.content.LocationDB
import com.github.skytoph.simpleweather.data.weather.cache.model.content.forecast.ForecastDB
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.ForecastToCurrentDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.FindForecastMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.ForecastDBToDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.ForecastToIndicatorsMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import javax.inject.Inject

interface ContentDBToDataMapper : Mapper<ContentData> {
    fun map(location: List<LocationDB>, airQuality: Int, forecast: ForecastDB): ContentData

    class Base @Inject constructor(
        private val currentMapper: ForecastToCurrentDataMapper,
        private val indicatorsMapper: ForecastToIndicatorsMapper,
        private val horizonMapper: HorizonDataMapper,
        private val forecastMapper: ForecastDBToDataMapper,
        private val findForecast: FindForecastMapper,
    ) : ContentDBToDataMapper {

        override fun map(
            location: List<LocationDB>,
            airQuality: Int,
            forecast: ForecastDB,
        ): ContentData {
            val currentForecast = forecast.findWeather(findForecast)
            return ContentData(
                currentMapper.map(currentForecast, location),
                indicatorsMapper.map(currentForecast, airQuality),
                forecast.findHorizon(findForecast).map(horizonMapper),
                forecast.map(forecastMapper)
            )
        }
    }
}
