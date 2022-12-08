package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long): String
    fun dateAndTime(seconds: Long, timeZone: TimeZone = TimeZone.getTimeZone("GMT")): String
    fun dayInWeek(seconds: Long): String
    fun duration(seconds: Long): Pair<Int, Int>

    class Base @Inject constructor(
        private val resources: LocaleProvider,
        private val patterns: FormatPatterns,
    ) : TimeFormatter {

        override fun timeFull(seconds: Long): String =
            format(patterns.hours(), seconds)

        override fun dateAndTime(seconds: Long, timeZone: TimeZone): String =
            format(patterns.dateAndHours(), seconds, timeZone)

        override fun dayInWeek(seconds: Long): String =
            SimpleDateFormat(patterns.dayInWeek(), resources.locale())
                .format(Date(TimeUnit.SECONDS.toMillis(seconds))).capitalize()

        override fun duration(seconds: Long) =
            Pair((seconds / 3600).toInt(), ((seconds % 3600) / 60).toInt())

        private fun format(
            pattern: String,
            seconds: Long,
            timeZone: TimeZone = TimeZone.getTimeZone(patterns.timezoneDefault()),
        ) = SimpleDateFormat(pattern, resources.locale())
            .also { it.timeZone = timeZone }
            .format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        private fun String.capitalize() =
            this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}
