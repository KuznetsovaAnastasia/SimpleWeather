package com.github.skytoph.simpleweather.core.provider

import android.content.SharedPreferences

interface PreferencesProvider {
    fun preferences(name: String): SharedPreferences
    fun defaultPreferences(): SharedPreferences
}
