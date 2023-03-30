package com.github.skytoph.simpleweather.domain.weather

interface SaveStateRefreshed {
    fun saveStateRefreshed(refreshLocation: RefreshLocation.SaveRefreshed)
}