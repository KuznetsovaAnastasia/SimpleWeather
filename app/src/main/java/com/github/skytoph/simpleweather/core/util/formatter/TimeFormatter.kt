package com.github.skytoph.simpleweather.core.util.formatter

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeFormatter {
    fun timeFull(seconds: Long): String
    fun timeShort(seconds: Long): String
    fun duration(seconds: Long): String

    class Base @Inject constructor() : TimeFormatter {

        override fun timeFull(seconds: Long): String =
            SimpleDateFormat("hh:mm a").format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun timeShort(seconds: Long): String =
            SimpleDateFormat("hh:mm").format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun duration(seconds: Long): String =
            String.format("%dH %02dM", seconds / 3600, (seconds % 3600) / 60)
    }
}
