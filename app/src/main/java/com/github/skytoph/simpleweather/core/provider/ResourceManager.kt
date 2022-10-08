package com.github.skytoph.simpleweather.core.provider

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import com.github.skytoph.simpleweather.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

interface ResourceManager : PreferencesProvider, ResourceProvider {

    @Singleton
    class Base @Inject constructor(@ApplicationContext private val context: Context) :
        ResourceManager {

        override fun color(id: Int) = ResourcesCompat.getColor(context.resources, id, null)
        override fun dimensionPixel(id: Int): Int = context.resources.getDimensionPixelSize(id)
        override fun string(id: Int): String = context.resources.getString(id)
        override fun string(id: Int, vararg args: String): String =
            context.resources.getString(id, *args)

        override fun drawable(id: Int): Drawable? =
            ResourcesCompat.getDrawable(context.resources, id, null)

        override fun preferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

        override fun defaultPreferences(): SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)
    }
}