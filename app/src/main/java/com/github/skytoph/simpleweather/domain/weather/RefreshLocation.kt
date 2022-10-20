package com.github.skytoph.simpleweather.domain.weather

import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import javax.inject.Inject

interface RefreshLocation {
    interface SaveIntention {
        fun saveIntention(id: String)
    }

    interface SaveRefreshed {
        fun locationRefreshed(id: String)
        fun intentionSaved(id: String): Boolean
    }

    interface Mutable : SaveIntention, SaveRefreshed, RefreshLocation

    class Base @Inject constructor(preferencesProvider: PreferencesProvider) : Mutable {
        private val preferences = preferencesProvider.preferences(filename)

        override fun saveIntention(id: String) {
            preferences.edit().putBoolean(key(id), true).apply()
        }

        override fun locationRefreshed(id: String) {
            preferences.edit().putBoolean(key(id), false).apply()
        }

        override fun intentionSaved(id: String) = preferences.getBoolean(key(id), false)

        private fun key(id: String) = default_key + id

        companion object {
            private const val filename = "refresh_location_intention"
            private const val default_key = "refresh_location_"
        }
    }
}