package com.github.skytoph.simpleweather.core.util.formatter

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long, locale: Locale = Locale.ENGLISH): String
    fun timeShort(seconds: Long, locale: Locale = Locale.ENGLISH): String
    fun dayInWeek(seconds: Long, locale: Locale = Locale.ENGLISH): String
    fun duration(seconds: Long): String

    class Base @Inject constructor() : TimeFormatter {

        override fun timeFull(seconds: Long, locale: Locale): String =
            SimpleDateFormat("hh:mm a", locale).format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun timeShort(seconds: Long, locale: Locale): String =
            SimpleDateFormat("hh:mm", locale).format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun dayInWeek(seconds: Long, locale: Locale): String =
            SimpleDateFormat("EEEE", locale).format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun duration(seconds: Long): String =
            String.format("%dH %02dM", seconds / 3600, (seconds % 3600) / 60)
    }
}
