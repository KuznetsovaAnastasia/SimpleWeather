package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.data.TimeProvider
import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import javax.inject.Inject

interface CompareTimeWithCurrent {
    fun sameHour(seconds: Long): Boolean

    class Base @Inject constructor(private val timeProvider: TimeProvider) : CompareTimeWithCurrent {

        override fun sameHour(seconds: Long): Boolean =
            RoundedTime(seconds).roundToHours() == timeProvider.currentHoursInSeconds()
    }
}

interface CurrentTimeComparable {
    fun updatedLately(mapper: CompareTimeWithCurrent): Boolean
}