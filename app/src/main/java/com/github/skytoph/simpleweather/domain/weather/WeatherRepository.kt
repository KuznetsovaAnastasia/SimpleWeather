package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.domain.weather.mapper.DataUpdatedLatelyCriteria

interface WeatherRepository {

    interface RefreshAll {
        suspend fun refreshAll(criteria: DataUpdatedLatelyCriteria = DataUpdatedLatelyCriteria.BASE)
    }

    interface Save {
        suspend fun saveWeather()
    }

    interface Contains {
        suspend fun contains(id: String): Boolean
    }

    interface Delete {
        suspend fun delete(id: String)
    }

    interface Mutable : RefreshAll, Delete, Save, Get {
        suspend fun updateLocationName(id: String): WeatherData
        fun cachedIDs(): List<String>
    }

    interface Get {
        suspend fun getCloudWeather(id: String): WeatherData
    }

    interface Update : Get {
        suspend fun updateCloudWeather(id: String): WeatherData
        suspend fun getCachedWeather(id: String): WeatherData
    }

    interface Base : Mutable, Update, RefreshAll, Save, Contains
}