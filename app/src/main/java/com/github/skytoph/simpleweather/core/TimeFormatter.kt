package com.github.skytoph.simpleweather.core

import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

interface TimeFormatter {
    fun formatTime(seconds: Long): String
    fun formatDuration(seconds: Long): String

    class Base : TimeFormatter {

        override fun formatTime(seconds: Long): String =
            SimpleDateFormat("hh:mm a").format(Date(TimeUnit.SECONDS.toMillis(seconds)))

        override fun formatDuration(seconds: Long): String =
            String.format("%dH %02dM", seconds / 3600, (seconds % 3600) / 60)

    }

}
