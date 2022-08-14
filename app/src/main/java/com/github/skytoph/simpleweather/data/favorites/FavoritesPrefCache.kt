package com.github.skytoph.simpleweather.data.favorites

import com.github.skytoph.simpleweather.core.data.Read
import com.github.skytoph.simpleweather.core.data.Save
import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import javax.inject.Inject
import javax.inject.Singleton

interface FavoritesPrefCache : Read<List<String>>, Save<String> {

    @Singleton
    class Base @Inject constructor(private val preferencesProvider: PreferencesProvider) : FavoritesPrefCache {

        override fun read(): List<String> =
            preferencesProvider.providePreferences(FAVORITES_FILENAME)
                .getStringSet(FAVORITES_KEY, null)?.toList() ?:
                emptyList()

        override fun save(data: String) {
            preferencesProvider.providePreferences(FAVORITES_FILENAME)
                .edit()
                .putStringSet(FAVORITES_KEY, read().toMutableSet().apply { add(data) })
                .apply()
        }

        companion object {
            const val FAVORITES_FILENAME = "favoriteLocationsFile"
            const val FAVORITES_KEY = "favoriteLocationsKey"
        }
    }

    class Mock : FavoritesPrefCache {
        override fun read(): List<String> = listOf("47.6933814,32.5106596")

        override fun save(data: String) = Unit
    }
}