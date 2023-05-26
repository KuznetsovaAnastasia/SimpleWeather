package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.data.weather.model.WeatherData
import com.github.skytoph.simpleweather.domain.weather.mapper.UpdatedLately

interface WeatherRepository {

    interface RefreshAll {
        suspend fun refreshAll(criteria: UpdatedLately = UpdatedLately.LastDay)
    }

    interface Save {
        suspend fun saveWeather()
        suspend fun checkReachingLimit(limit: Int)
        suspend fun cachedId(placeId: String): String
    }

    interface Delete {
        suspend fun delete(id: String)
    }

    interface Mutable : RefreshAll, Delete, Save, Get {
        fun cachedIDs(): List<String>
    }

    interface Get {
        suspend fun getCloudWeather(id: String): WeatherData
    }

    interface Update : Get {
        suspend fun updateCloudWeather(id: String): WeatherData
        suspend fun getCachedWeather(id: String): WeatherData
    }

    interface Base : Mutable, Update, RefreshAll, Save
}