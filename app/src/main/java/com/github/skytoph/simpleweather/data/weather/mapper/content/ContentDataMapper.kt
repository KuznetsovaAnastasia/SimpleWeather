package com.github.skytoph.simpleweather.data.weather.mapper.content

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.ForecastDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.indicators.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import javax.inject.Inject

interface ContentDataMapper : Mapper<ContentData> {
    fun <
            C : Mappable<CurrentWeatherData, CurrentWeatherDataMapper>,
            I : Mappable<IndicatorsData, IndicatorsDataMapper>,
            H : Mappable<HorizonData, HorizonDataMapper>,
            F : Mappable<ForecastData, ForecastDataMapper>,
            > map(
        current: C,
        indicators: I,
        horizon: H,
        forecast: F,
    ): ContentData

    class Base @Inject constructor(
        private val currentMapper: CurrentWeatherDataMapper,
        private val indicatorsMapper: IndicatorsDataMapper,
        private val horizonMapper: HorizonDataMapper,
        private val forecastMapper: ForecastDataMapper,
    ) : ContentDataMapper {

        override fun <C : Mappable<CurrentWeatherData, CurrentWeatherDataMapper>, I : Mappable<IndicatorsData, IndicatorsDataMapper>, H : Mappable<HorizonData, HorizonDataMapper>, F : Mappable<ForecastData, ForecastDataMapper>> map(
            current: C,
            indicators: I,
            horizon: H,
            forecast: F,
        ): ContentData = ContentData(current.map(currentMapper),
            indicators.map(indicatorsMapper),
            horizon.map(horizonMapper),
            forecast.map(forecastMapper))
    }
}
