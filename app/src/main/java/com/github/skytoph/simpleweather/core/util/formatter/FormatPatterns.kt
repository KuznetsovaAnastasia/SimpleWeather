package com.github.skytoph.simpleweather.core.util.formatter

import javax.inject.Inject

interface FormatPatterns {
    fun hours(): String
    fun dateAndHours(): String
    fun dayInWeek(): String
    fun timezoneDefault(): String

    class Base @Inject constructor(private val format: TimeFormat) : FormatPatterns {

        override fun hours(): String = if (format.is24HourChosen()) "HH:mm" else "hh:mm a"
        override fun dateAndHours(): String = "dd.MM, ${hours()}"
        override fun dayInWeek(): String = "EEEE"
        override fun timezoneDefault(): String = "GMT"
    }
}
