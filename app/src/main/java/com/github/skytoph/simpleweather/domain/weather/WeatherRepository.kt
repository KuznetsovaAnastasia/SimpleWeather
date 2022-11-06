package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.model.WeatherData

interface WeatherRepository {

    interface RefreshAll {
        suspend fun refreshAll()
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

    interface Mutable : RefreshAll, Delete {
        fun cachedIDs(): List<String>
    }

    interface Update {
        suspend fun updateCloudWeather(id: String): WeatherData
        suspend fun updateLocationName(id: String): WeatherData
        suspend fun getCachedWeather(id: String): WeatherData
        suspend fun getCloudWeather(id: String): WeatherData
    }

    interface Base : Mutable, Update, RefreshAll, Save, Contains
}