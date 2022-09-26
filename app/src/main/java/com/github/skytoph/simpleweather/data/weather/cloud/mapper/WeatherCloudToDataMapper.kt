package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.airquality.cloud.AirQualityCloud
import com.github.skytoph.simpleweather.data.location.cloud.IdMapper
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloud
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.weather.cloud.model.*
import com.github.skytoph.simpleweather.data.weather.mapper.*
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherCloudToDataMapper : Mapper<WeatherData> {
    fun map(
        weatherCloud: WeatherCloud,
        airQualityCloud: AirQualityCloud,
        locationCloud: PlaceCloud,
        favorite: Boolean = false,
    ): WeatherData

    class Base @Inject constructor(
        private val idMapper: IdMapper,
        private val currentWeatherDataMapper: CurrentWeatherDataMapper,
        private val indicatorsDataMapper: IndicatorsDataMapper,
        private val horizonDataMapper: HorizonDataMapper,
        private val alertsMapper: AlertsDataMapper,
        private val hourlyMapper: HourlyForecastListDataMapper,
        private val dailyMapper: DailyForecastListDataMapper,
    ) : WeatherCloudToDataMapper {

        override fun map(
            weatherCloud: WeatherCloud,
            airQualityCloud: AirQualityCloud,
            locationCloud: PlaceCloud,
            favorite: Boolean,
        ): WeatherData = weatherCloud.map(object : WeatherCloudMapper {

            override fun map(
                current: CurrentWeatherCloud,
                hourly: List<HourlyForecastCloud>,
                daily: List<DailyForecastCloud>,
                alerts: List<AlertCloud>,
            ): WeatherData = locationCloud.map(object : PlaceCloudMapper {

                override fun map(name: String, lat: Double, lng: Double) =

                    current.map(object : CurrentCloudToDataMapper {

                        override fun map(
                            dt: Long,
                            temp: Double,
                            sunrise: Long,
                            sunset: Long,
                            uvi: Double,
                            weather: WeatherTypeCloud,
                        ): WeatherData {
                            val pop = hourly[0].map()
                            return WeatherData.Info(
                                idMapper.map(lat, lng),
                                currentWeatherDataMapper.map(weather.map(), temp, name),
                                indicatorsDataMapper.map(dt, temp, pop, airQualityCloud.map()),
                                horizonDataMapper.map(sunrise, sunset, dt),
                                alertsMapper.map(alerts, pop),
                                hourlyMapper.map(hourly),
                                dailyMapper.map(daily),
                            )
                        }
                    })
            })
        })
    }
}