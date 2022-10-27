package com.github.skytoph.simpleweather.core.data

import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface TimeProvider {
    fun currentHoursInSeconds(): Long

    class Base @Inject constructor() : TimeProvider {

        override fun currentHoursInSeconds(): Long =
            TimeUnit.HOURS.toSeconds(
                TimeUnit.MILLISECONDS.toHours(
                    Calendar.getInstance().timeInMillis))
    }
}