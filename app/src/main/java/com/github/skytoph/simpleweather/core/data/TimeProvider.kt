package com.github.skytoph.simpleweather.core.data

import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeProvider {
    fun currentHoursInSeconds(): Long
    fun currentDayInSeconds(): Long

    class Base @Inject constructor() : TimeProvider {

        override fun currentHoursInSeconds(): Long =
            TimeUnit.HOURS.toSeconds(
                TimeUnit.MILLISECONDS.toHours(
                    Calendar.getInstance().timeInMillis))

        override fun currentDayInSeconds(): Long =
            TimeUnit.DAYS.toSeconds(
                TimeUnit.MILLISECONDS.toDays(
                    Calendar.getInstance().timeInMillis))
    }
}