package com.github.skytoph.simpleweather.data.pages

import com.github.skytoph.simpleweather.core.data.Read
import com.github.skytoph.simpleweather.core.data.Save
import com.github.skytoph.simpleweather.core.provider.PreferencesProvider
import javax.inject.Inject

interface PagesDataSource : Read<Int>, Save<Int> {

    class Base @Inject constructor(preferencesProvider: PreferencesProvider) : PagesDataSource {

        private val preferences = preferencesProvider.preferences(filename)

        override fun read(): Int = preferences.getInt(key, 0)

        override fun save(data: Int) = preferences.edit().putInt(key, data).apply()

        companion object {
            private const val filename = "page_position"
            private const val key = "page"
        }
    }
}