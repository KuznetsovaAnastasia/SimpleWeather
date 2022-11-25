package com.github.skytoph.simpleweather.core.util.time

class RoundedTime(private val seconds: Long) : RoundTime {

    override fun roundToDay(): Long = seconds - seconds % dayDivider

    override fun roundToHours(): Long = seconds - seconds % hourDivider

    private companion object {
        const val dayDivider = 86_400
        const val hourDivider = 3_600
    }
}

interface RoundTime {
    fun roundToDay(): Long
    fun roundToHours(): Long
}