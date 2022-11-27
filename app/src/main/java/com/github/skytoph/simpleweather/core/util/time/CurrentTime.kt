package com.github.skytoph.simpleweather.core.util.time

import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

interface CurrentTime {
    fun inMillis(): Long
    fun inSeconds(): Long
    fun hoursInSeconds(): Long
    fun dayInSeconds(): Long

    class Base @Inject constructor() : CurrentTime {

        override fun inMillis(): Long = Calendar.getInstance().timeInMillis

        override fun inSeconds(): Long =
            TimeUnit.MILLISECONDS.toSeconds(inMillis())

        override fun hoursInSeconds(): Long =
            RoundedTime(TimeMillis.Base(inMillis())).roundToHours()

        override fun dayInSeconds(): Long =
            RoundedTime(TimeMillis.Base(inMillis())).roundToDay()
    }
}