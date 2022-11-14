package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastFilter
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastFilter
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import com.github.skytoph.simpleweather.data.weather.model.content.current.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.DailyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.HourlyForecastData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.WarningData
import com.github.skytoph.simpleweather.data.weather.model.content.horizon.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.content.indicators.IndicatorsData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastDomainMapper
import com.github.skytoph.simpleweather.domain.weather.model.*
import javax.inject.Inject

interface WeatherDataToDomainMapper : Mapper<WeatherDomain> {

    fun map(identifier: IdentifierData, time: ForecastTimeData, content: ContentData): WeatherDomain

    class Base @Inject constructor(
        private val indicatorsMapper: IndicatorsDomainMapper,
        private val horizonDomainMapper: HorizonDataToDomainMapper,
        private val hourlyFilter: HourlyForecastFilter,
        private val dailyFilter: DailyForecastFilter,
        private val sunMapper: SunPositionMapper,
        private val warningMapper: WarningsDomainMapper,
    ) : WeatherDataToDomainMapper, Mapper.ToDomain<WeatherDomain>() {

        override fun map(
            identifier: IdentifierData, time: ForecastTimeData, content: ContentData,
        ): WeatherDomain {
            val timezone = time.map(object : TimezoneMapper {
                override fun map(timezoneOffset: Int, timezone: String) =
                    Timezone.Base(timezone, timezoneOffset)
            })
            val forecastTime = time.map(object : TimeDomainMapper {
                override fun map(time: Long): Long = time
            })
            val currentMapper = object : CurrentWeatherDataToDomainMapper {
                override fun map(
                    weatherId: Int, temperature: Double, location: String,
                ) = CurrentWeatherDomain(location, temperature, weatherId, forecastTime)
            }
            val contentMapper = object : ContentDataToDomainMapper {
                override fun map(
                    currentWeather: CurrentWeatherData,
                    indicators: IndicatorsData,
                    horizon: HorizonData,
                    forecast: ForecastData,
                ): ContentDomain {
                    val horizonMapper = object : HorizonDomainMapper {
                        override fun map(sunrise: Long, sunset: Long): HorizonDomain =
                            sunMapper.map(sunrise, sunset, timezone).map(horizonDomainMapper)
                    }
                    val hourlyMapper = object : HourlyForecastDomainMapper {
                        override fun map(
                            time: Long, temp: Double, weatherId: Int, pop: Double,
                        ) = HourlyDomain(timezone.withOffset(time), temp, weatherId, pop)
                    }
                    val dailyMapper = object : DailyForecastDomainMapper {
                        override fun map(
                            time: Long, temp: Pair<Double, Double>, weatherId: Int, pop: Double,
                        ) = DailyDomain(timezone.withOffset(time), temp, weatherId, pop)
                    }
                    val forecastMapper = object : ForecastDomainMapper {
                        override fun map(
                            alerts: List<WarningData>,
                            hourlyForecast: List<HourlyForecastData>,
                            dailyForecast: List<DailyForecastData>,
                        ): ForecastDomain = ForecastDomain(
                            warningMapper.map(alerts, timezone, forecast, indicators.map()),
                            hourlyFilter.filter(hourlyForecast).map { it.map(hourlyMapper) },
                            dailyFilter.filter(dailyForecast).map { it.map(dailyMapper) })
                    }
                    return ContentDomain(
                        currentWeather.map(currentMapper),
                        indicatorsMapper.map(indicators, timezone),
                        horizon.map(horizonMapper),
                        forecast.map(forecastMapper)
                    )
                }
            }
            return WeatherDomain(identifier.map(), content.map(contentMapper))
        }
    }
}