package com.github.skytoph.simpleweather.core.provider

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.*
import javax.inject.Inject

interface ResourceManager : PreferencesProvider, ResourceProvider, LocaleProvider {

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

        override fun locale(): Locale =
            Locale.getDefault() ?: Locale.ENGLISH
    }
}