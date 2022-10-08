package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.provider.ResourceManager
import javax.inject.Inject

interface TemperatureFormat {
    fun isCelsiusChosen(): Boolean

    class Base @Inject constructor(private val resources: ResourceManager) : TemperatureFormat {

        override fun isCelsiusChosen(): Boolean {
            val celsius = resources.string(R.string.unit_celsius)
            val unit = resources.defaultPreferences()
                .getString(resources.string(R.string.key_units), celsius)
            return celsius == unit
        }
    }
}