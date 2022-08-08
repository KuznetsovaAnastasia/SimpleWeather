package com.github.skytoph.simpleweather.core.provider

import android.content.Context
import android.content.SharedPreferences

interface PreferencesProvider {
    fun providePreferences(name: String): SharedPreferences

    class Base(private val context: Context) : PreferencesProvider {
        override fun providePreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}
