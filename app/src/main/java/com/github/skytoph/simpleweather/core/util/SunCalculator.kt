package com.github.skytoph.simpleweather.core.util

import java.util.concurrent.TimeUnit

interface SunCalculator {
    fun duration(start: Long, end: Long): Long
    fun remainingDaylight(sunrise: Long, sunset: Long, current: Long): Long
    fun sunPosition(sunrise: Long, sunset: Long, current: Long): Double

    class Base : SunCalculator {

        override fun duration(start: Long, end: Long): Long =
            if (end > start) end - start
            else 0

        override fun remainingDaylight(sunrise: Long, sunset: Long, current: Long): Long =
            when {
                current in sunrise until sunset -> sunset - current
                current < sunrise && sunset > sunrise -> sunset - sunrise
                else -> 0
            }

        override fun sunPosition(
            sunrise: Long,
            sunset: Long,
            current: Long,
        ): Double {
            val daylightLength = duration(sunrise, sunset).toDouble()
            val nightLength by lazy { DAY_IN_SECONDS - daylightLength }

            return when {
                current in sunrise until sunset ->
                    duration(sunrise, current).toDouble() / daylightLength

                current > sunset ->
                    -duration(sunset, current).toDouble() / nightLength

                current < sunrise ->
                    duration(current, sunrise).toDouble() / nightLength - 1

                else -> 0.0
            }
        }

        companion object {
            val DAY_IN_SECONDS = TimeUnit.DAYS.toSeconds(1)
        }
    }
}
