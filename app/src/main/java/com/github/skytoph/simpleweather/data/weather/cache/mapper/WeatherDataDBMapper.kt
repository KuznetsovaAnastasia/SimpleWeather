package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.IndicatorsData
import com.github.skytoph.simpleweather.data.weather.model.LocationData

interface WeatherDataDBMapper : Mapper<WeatherDB> {
    fun map(
        location: LocationData,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        dataBase: DataBase<WeatherDB>,
    ): WeatherDB

    class Base(
        private val currentMapper: CurrentDBMapper,
        private val locationMapper: LocationDBMapper,
        private val indicatorsMapper: IndicatorsDBMapper,
        private val horizonMapper: HorizonDBMapper,
    ) : WeatherDataDBMapper {

        override fun map(
            location: LocationData,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            dataBase: DataBase<WeatherDB>,
        ): WeatherDB = dataBase.createObject(location.map()).apply {
            this.current = currentWeatherData.map(currentMapper)
            this.location = location.map(locationMapper)
            this.indicators = indicatorsData.map(indicatorsMapper)
            this.horizon = horizonData.map(horizonMapper)
        }
    }
}
