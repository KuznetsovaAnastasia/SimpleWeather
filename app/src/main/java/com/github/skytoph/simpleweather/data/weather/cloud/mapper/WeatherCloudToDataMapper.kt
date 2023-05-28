package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.airquality.cloud.AirQualityCloud
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceData
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.weather.cloud.model.*
import com.github.skytoph.simpleweather.data.weather.mapper.content.current.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.DailyForecastListDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.HourlyForecastListDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.forecast.WarningListDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.content.horizon.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.data.weather.model.content.ContentData
import com.github.skytoph.simpleweather.data.weather.model.content.forecast.ForecastData
import com.github.skytoph.simpleweather.data.weather.model.identifier.IdentifierData
import com.github.skytoph.simpleweather.data.weather.model.time.ForecastTimeData
import javax.inject.Inject

interface WeatherCloudToDataMapper : Mapper<WeatherData> {
    fun map(
        weatherCloud: WeatherCloud,
        airQualityCloud: AirQualityCloud,
        locationCloud: PlaceData,
        favorite: Boolean = false,
    ): WeatherData

    class Base @Inject constructor(
        private val idMapper: IdMapper,
        private val currentMapper: CurrentWeatherDataMapper,
        private val indicatorsMapper: IndicatorsCloudToDataMapper,
        private val horizonMapper: HorizonDataMapper,
        private val warningsMapper: WarningListDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
        private val dailyMapper: DailyForecastListDataMapper,
    ) : WeatherCloudToDataMapper {

        override fun map(
            weatherCloud: WeatherCloud,
            airQualityCloud: AirQualityCloud,
            locationCloud: PlaceData,
            favorite: Boolean,
        ): WeatherData = weatherCloud.map(object : WeatherCloudMapper {

            override fun map(
                current: CurrentWeatherCloud,
                timezoneOffset: Int,
                timezone: String,
                hourly: List<HourlyForecastCloud>,
                daily: List<DailyForecastCloud>,
                alerts: List<AlertCloud>,
            ): WeatherData = locationCloud.map(object : PlaceCloudMapper {

                override fun map(
                    placeId: String, location: Map<String, String>, lat: Double, lng: Double
                ) = current.map(object : CurrentCloudToDataMapper {

                    override fun map(
                        dt: Long,
                        temp: Double,
                        sunrise: Long,
                        sunset: Long,
                        uvi: Double,
                        weather: Int,
                    ): WeatherData {
                        val pop = hourly[0].map()
                        return WeatherData(
                            IdentifierData(placeId, lat, lng, favorite),
                            ForecastTimeData(dt, timezoneOffset, timezone),
                            ContentData(
                                currentMapper.map(weather, temp, location),
                                indicatorsMapper.map(temp, pop, airQualityCloud.map()),
                                horizonMapper.map(sunrise, sunset),
                                ForecastData(
                                    warningsMapper.map(alerts),
                                    hourlyMapper.map(hourly),
                                    dailyMapper.map(daily)
                                )
                            )
                        )
                    }
                })
            })
        })
    }
}