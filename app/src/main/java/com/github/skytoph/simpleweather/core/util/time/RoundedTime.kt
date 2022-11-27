package com.github.skytoph.simpleweather.core.util.time

import java.util.concurrent.TimeUnit

class RoundedTime(private val time: TimeMillis) : RoundTime {

    override fun roundToDay(): Long = time.rounded(dayDivider)

    override fun roundToHours(): Long = time.rounded(hourDivider)

    private companion object {
        const val dayDivider = 86_400 //todo replace
        const val hourDivider = 3_600
    }
}

abstract class TimeMillis(private val time: Long) {
    fun rounded(divider: Int) = time - time % divider

    class Base(millis: Long) : TimeMillis(TimeUnit.MILLISECONDS.toSeconds(millis))
    class FromSeconds(seconds: Long) : TimeMillis(seconds)
}

interface RoundTime {
    fun roundToDay(): Long
    fun roundToHours(): Long
}