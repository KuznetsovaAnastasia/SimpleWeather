package com.github.skytoph.simpleweather.core.util.formatter

import com.github.skytoph.simpleweather.R
import com.github.skytoph.simpleweather.core.provider.ResourceProvider
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long, locale: Locale = Locale.ENGLISH): String
    fun dayInWeek(seconds: Long, locale: Locale = Locale.ENGLISH): String
    fun duration(seconds: Long): String

    class Base @Inject constructor(
        private val resources: ResourceProvider,
        private val format: TimeFormat,
    ) : TimeFormatter {

        override fun timeFull(seconds: Long, locale: Locale): String {
            val timePattern = if (format.is24HourChosen()) "HH:mm" else "hh:mm a"
            return SimpleDateFormat(timePattern, locale)
                .format(Date(TimeUnit.SECONDS.toMillis(seconds)))
        }

        override fun dayInWeek(seconds: Long, locale: Locale): String =
            SimpleDateFormat("EEEE", locale).format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun duration(seconds: Long): String =
            String.format(resources.string(R.string.time_duration_format),
                seconds / 3600,
                (seconds % 3600) / 60)
    }
}
