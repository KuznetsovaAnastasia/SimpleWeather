package com.github.skytoph.simpleweather.core.data

import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeProvider {
    fun currentTimeInMillis(): Long
    fun currentTimeInSeconds(): Long
    fun currentHoursInSeconds(): Long
    fun currentDayInSeconds(): Long

    class Base @Inject constructor() : TimeProvider {

        override fun currentTimeInMillis(): Long = Calendar.getInstance().timeInMillis

        override fun currentTimeInSeconds(): Long =
            TimeUnit.MILLISECONDS.toSeconds(currentTimeInMillis())

        override fun currentHoursInSeconds(): Long =
            RoundedTime(currentTimeInSeconds()).roundToHours()

        override fun currentDayInSeconds(): Long =
            RoundedTime(currentTimeInSeconds()).roundToDay()
    }
}