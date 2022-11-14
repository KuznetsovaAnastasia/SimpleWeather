package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.core.provider.LocaleProvider
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long): String
    fun dateAndTime(seconds: Long): String
    fun dayInWeek(seconds: Long): String
    fun duration(seconds: Long): Pair<Int, Int>

    class Base @Inject constructor(
        private val resources: LocaleProvider,
        private val patterns: FormatPatterns,
    ) : TimeFormatter {

        override fun timeFull(seconds: Long): String =
            SimpleDateFormat(patterns.hours(), resources.locale())
                .also { it.timeZone = TimeZone.getTimeZone(patterns.timezone()) }
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun dateAndTime(seconds: Long): String =
            SimpleDateFormat(patterns.dateAndHours(), resources.locale())
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun dayInWeek(seconds: Long): String =
            SimpleDateFormat(patterns.dayInWeek(), resources.locale())
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))
                .capitalize()

        override fun duration(seconds: Long) =
            Pair((seconds / 3600).toInt(), ((seconds % 3600) / 60).toInt())

        private fun String.capitalize() =
            this.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }

    }
}
