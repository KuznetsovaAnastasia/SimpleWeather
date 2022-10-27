package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long): String
    fun dayInWeek(seconds: Long): String
    fun duration(seconds: Long): Pair<Int, Int>

    class Base @Inject constructor(
        private val resources: LocaleProvider,
        private val format: TimeFormat,
    ) : TimeFormatter {

        override fun timeFull(seconds: Long): String {
            val timePattern = if (format.is24HourChosen()) "HH:mm" else "hh:mm a"
            return SimpleDateFormat(timePattern, resources.locale())
                .also { it.timeZone = TimeZone.getTimeZone("GMT") }
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))
        }

        override fun dayInWeek(seconds: Long): String =
            SimpleDateFormat("EEEE", resources.locale())
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun duration(seconds: Long) =
            Pair((seconds / 3600).toInt(), ((seconds % 3600) / 60).toInt())
    }
}
