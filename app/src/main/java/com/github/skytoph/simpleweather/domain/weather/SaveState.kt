package com.github.skytoph.simpleweather.domain.weather

interface SaveState {
    fun saveState(refreshLocation: RefreshLocation.SaveRefreshed)
}