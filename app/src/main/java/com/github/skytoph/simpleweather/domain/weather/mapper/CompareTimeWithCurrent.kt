package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import com.github.skytoph.simpleweather.core.util.time.TimeSeconds
import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import javax.inject.Inject

interface CompareTimeWithCurrent {
    fun compare(seconds: Long, criteria: DataUpdatedLatelyCriteria): Boolean

    class Base @Inject constructor(private val currentTime: CurrentTime) :
        CompareTimeWithCurrent {

        override fun compare(seconds: Long, criteria: DataUpdatedLatelyCriteria): Boolean =
            when (criteria) {
                DataUpdatedLatelyCriteria.HOURS -> CompareTime.Hours(currentTime).compare(seconds)
                DataUpdatedLatelyCriteria.DAYS -> CompareTime.Day(currentTime).compare(seconds)
                else -> false
            }
    }
}

abstract class CompareTime(protected val time: CurrentTime) {
    abstract fun compare(seconds: Long): Boolean

    class Hours(time: CurrentTime) : CompareTime(time) {
        override fun compare(seconds: Long) =
            RoundedTime(TimeSeconds.Base(seconds)).roundToHours() == time.hoursInSeconds()
    }

    class Day(time: CurrentTime) : CompareTime(time) {
        override fun compare(seconds: Long): Boolean =
            RoundedTime(TimeSeconds.Base(seconds)).roundToDay() == time.dayInSeconds()
    }
}