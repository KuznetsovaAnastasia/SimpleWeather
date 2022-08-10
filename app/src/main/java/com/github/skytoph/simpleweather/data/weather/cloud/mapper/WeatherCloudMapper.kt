package com.github.skytoph.simpleweather.data.weather.cloud.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.data.airquality.cloud.AirQualityCloud
import com.github.skytoph.simpleweather.data.location.cloud.PlaceCloud
import com.github.skytoph.simpleweather.data.location.mapper.PlaceCloudMapper
import com.github.skytoph.simpleweather.data.location.mapper.LocationDataMapper
import com.github.skytoph.simpleweather.data.weather.cloud.model.*
import com.github.skytoph.simpleweather.data.weather.mapper.CurrentWeatherDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.HorizonDataMapper
import com.github.skytoph.simpleweather.data.weather.mapper.IndicatorsDataMapper
import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import javax.inject.Inject

interface WeatherCloudMapper : Mapper<WeatherData> {
    fun map(
        current: CurrentWeatherCloud,
        hourly: List<HourlyForecastCloud>,
        alerts: List<AlertCloud>,
    ): WeatherData
}

interface WeatherCloudToDataMapper : Mapper<WeatherData> {
    fun map(
        weatherCloud: WeatherCloud,
        airQualityCloud: AirQualityCloud,
        locationCloud: PlaceCloud,
        favorite: Boolean = false,
    ): WeatherData

    class Base @Inject constructor(
        private val locationDataMapper: LocationDataMapper,
        private val currentWeatherDataMapper: CurrentWeatherDataMapper,
        private val indicatorsDataMapper: IndicatorsDataMapper,
        private val horizonDataMapper: HorizonDataMapper,
        private val alertsMapper: AlertsDataMapper,
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
                alerts: List<AlertCloud>,
            ): WeatherData = locationCloud.map(object : PlaceCloudMapper.ToWeatherData {

                override fun map(id: String, name: String, lat: Double, lng: Double) =

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
                                locationDataMapper.map(id, lat, lng, name, favorite),
                                currentWeatherDataMapper.map(weather.map(), temp),
                                indicatorsDataMapper.map(dt, temp, pop, airQualityCloud.map()),
                                horizonDataMapper.map(sunrise, sunset, dt),
                                alertsMapper.map(alerts, pop)
                            )
                        }
                    })
            })
        })
    }
}