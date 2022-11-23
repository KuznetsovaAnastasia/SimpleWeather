package com.github.skytoph.simpleweather.core.util.time

import javax.inject.Inject

interface TimeConverter {
    fun roundToDay(seconds: Long): Long
    fun roundToHours(seconds: Long): Long

    class Base @Inject constructor() : TimeConverter {

        override fun roundToDay(seconds: Long): Long = seconds - seconds % dayDivider

        override fun roundToHours(seconds: Long): Long = seconds - seconds % hourDivider

        private companion object {
            const val dayDivider = 86_400
            const val hourDivider = 3_600
        }
    }
}