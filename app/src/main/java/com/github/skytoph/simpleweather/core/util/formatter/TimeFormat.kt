package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.provider.ResourceManager
import javax.inject.Inject

interface TimeFormat {
    fun is24HourChosen(): Boolean

    class Base @Inject constructor(private val resources: ResourceManager) : TimeFormat {

        override fun is24HourChosen(): Boolean = resources.defaultPreferences()
            .getBoolean(resources.string(R.string.key_time), true)
    }
}