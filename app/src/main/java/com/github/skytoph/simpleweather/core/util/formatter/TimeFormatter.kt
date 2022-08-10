package com.github.skytoph.simpleweather.core.util.formatter

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

interface TimeFormatter {
    fun formatTime(seconds: Long): String
    fun formatDuration(seconds: Long): String

    @Singleton
    class Base @Inject constructor() : TimeFormatter {

        override fun formatTime(seconds: Long): String =
            SimpleDateFormat("hh:mm a").format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun formatDuration(seconds: Long): String =
            String.format("%dH %02dM", seconds / 3600, (seconds % 3600) / 60)

    }

}
