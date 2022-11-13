package com.github.skytoph.simpleweather.data.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastFilter
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.FindForecastedPop
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
        private val currentMapper: CurrentWeatherDataToDomainMapper,
        private val horizonDomainMapper: HorizonDataToDomainMapper,
        private val findPopMapper: FindForecastedPop,
        private val hourlyFilter: HourlyForecastFilter,
        private val dailyFilter: DailyForecastFilter,
        private val sunMapper: SunPositionMapper,
    ) : WeatherDataToDomainMapper, Mapper.ToDomain<WeatherDomain>() {

        override fun map(
            identifier: IdentifierData, time: ForecastTimeData, content: ContentData,
        ): WeatherDomain {
            val timezone = time.map(object : TimezoneMapper {
                override fun map(timezoneOffset: Int) = TimezoneOffset.Base(timezoneOffset)
            })
            val forecastTime = time.map(object : TimeDomainMapper {
                override fun map(time: Long): Long = timezone.withOffset(time)
            })
            val contentMapper = object : ContentDataToDomainMapper {
                override fun map(
                    currentWeather: CurrentWeatherData,
                    indicators: IndicatorsData,
                    horizon: HorizonData,
                    forecast: ForecastData,
                ): ContentDomain {
                    val indicatorsMapper = object : IndicatorsDataToDomainMapper {
                        override fun map(
                            uvi: Double, precipitationProb: Double, airQuality: Int,
                        ) = IndicatorsDomain(forecastTime, uvi, precipitationProb, airQuality)
                    }
                    val horizonMapper = object : HorizonDomainMapper {
                        override fun map(sunrise: Long, sunset: Long): HorizonDomain =
                            sunMapper.map(sunrise, sunset, timezone).map(horizonDomainMapper)
                    }
                    val warningMapper = object : WarningDataToDomainMapper {
                        override fun map(
                            name: String, startTime: Long, endTime: Long, description: String,
                        ): WarningDomain {
                            val started = forecastTime > startTime
                            return WarningDomain(name,
                                timezone.withOffset(if (started) endTime else startTime),
                                started,
                                forecast.map(findPopMapper, startTime, indicators.map()),
                                description)
                        }
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
                            alerts.map { it.map(warningMapper) },
                            hourlyFilter.filter(hourlyForecast).map { it.map(hourlyMapper) },
                            dailyFilter.filter(dailyForecast).map { it.map(dailyMapper) })
                    }
                    return ContentDomain(
                        currentWeather.map(currentMapper),
                        indicators.map(indicatorsMapper),
                        horizon.map(horizonMapper),
                        forecast.map(forecastMapper)
                    )
                }
            }
            return WeatherDomain(
                identifier.map(),
                content.map(contentMapper)
            )
        }
    }
}