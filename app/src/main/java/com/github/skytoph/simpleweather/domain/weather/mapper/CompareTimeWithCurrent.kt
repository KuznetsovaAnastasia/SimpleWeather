package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.util.time.CurrentTime
import com.github.skytoph.simpleweather.core.util.time.RoundedTime
import com.github.skytoph.simpleweather.core.util.time.TimeSeconds
import javax.inject.Inject

interface CompareTimeWithCurrent {
    fun compare(seconds: Long, criteria: UpdatedLately): Boolean

    class Base @Inject constructor(private val currentTime: CurrentTime) :
        CompareTimeWithCurrent {

        override fun compare(seconds: Long, criteria: UpdatedLately): Boolean =
            criteria.map(currentTime).compare(seconds)
    }
}

abstract class ComparableTime {
    abstract fun compare(seconds: Long): Boolean

    class Hours(private val time: CurrentTime) : ComparableTime() {
        override fun compare(seconds: Long) =
            RoundedTime(TimeSeconds.Base(seconds)).roundToHours() == time.hoursInSeconds()
    }

    class Day(private val time: CurrentTime) : ComparableTime() {
        override fun compare(seconds: Long): Boolean =
            RoundedTime(TimeSeconds.Base(seconds)).roundToDay() == time.dayInSeconds()
    }

    class Any : ComparableTime() {
        override fun compare(seconds: Long): Boolean = true
    }
}