package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import com.github.skytoph.simpleweather.core.util.time.TimeMillis
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import javax.inject.Inject

interface CompareTimeWithCurrent {
    fun sameHour(seconds: Long): Boolean

    class Base @Inject constructor(private val currentTime: CurrentTime) :
        CompareTimeWithCurrent {

        override fun sameHour(seconds: Long): Boolean =
            RoundedTime(TimeMillis.FromSeconds(seconds)).roundToHours() == currentTime.hoursInSeconds()
    }
}

interface CurrentTimeComparable {
    fun updatedLately(mapper: CompareTimeWithCurrent): Boolean
}