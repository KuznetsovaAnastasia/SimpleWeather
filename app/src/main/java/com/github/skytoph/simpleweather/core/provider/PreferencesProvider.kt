package com.github.skytoph.simpleweather.core.provider

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface PreferencesProvider {
    fun providePreferences(name: String): SharedPreferences

    @Singleton
    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        PreferencesProvider {
        override fun providePreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}
