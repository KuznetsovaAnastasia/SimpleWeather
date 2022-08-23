package com.github.skytoph.simpleweather.data.weather.cache.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.data.DataBase
import com.github.skytoph.simpleweather.data.weather.cache.WarningDB
import com.github.skytoph.simpleweather.data.weather.cache.WeatherDB
import com.github.skytoph.simpleweather.data.weather.model.AlertData
import com.github.skytoph.simpleweather.data.weather.model.CurrentWeatherData
import com.github.skytoph.simpleweather.data.weather.model.HorizonData
import com.github.skytoph.simpleweather.data.weather.model.IndicatorsData
import io.realm.RealmList
import javax.inject.Inject

interface WeatherDataDBMapper : Mapper<WeatherDB> {
    fun map(
        id: String,
        currentWeatherData: CurrentWeatherData,
        indicatorsData: IndicatorsData,
        horizonData: HorizonData,
        alerts: List<AlertData>,
        dataBase: DataBase,
    ): WeatherDB

    class Base @Inject constructor(
        private val currentMapper: CurrentDBMapper,
        private val indicatorsMapper: IndicatorsDBMapper,
        private val horizonMapper: HorizonDBMapper,
        private val warningsDBMapper: WarningsDBMapper,
    ) : WeatherDataDBMapper {

        override fun map(
            id: String,
            currentWeatherData: CurrentWeatherData,
            indicatorsData: IndicatorsData,
            horizonData: HorizonData,
            alerts: List<AlertData>,
            dataBase: DataBase,
        ): WeatherDB = dataBase.createObject<WeatherDB>(id).apply {
            this.current = currentWeatherData.map(currentMapper)
            this.indicators = indicatorsData.map(indicatorsMapper)
            this.horizon = horizonData.map(horizonMapper)
        }.also { warningsDBMapper.map(alerts, dataBase, it) }
    }
}
