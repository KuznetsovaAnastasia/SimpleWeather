package com.github.skytoph.simpleweather.core

import com.github.skytoph.simpleweather.data.weather.mapper.SunPosition
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import java.util.concurrent.TimeUnit
import kotlin.math.exp

class SunPositionTest {


    @Test
    fun test_day_length() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(6)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = TimeUnit.HOURS.toSeconds(12).toInt()

        assertEquals(expected, sunPosition.dayLength())
    }

    @Test
    fun test_day_length_wrong() {
        val sunrise = TimeUnit.HOURS.toSeconds(18)
        val sunset = TimeUnit.HOURS.toSeconds(6)
        val current = TimeUnit.HOURS.toSeconds(6)

        assertThrows(IllegalStateException::class.java) { SunPosition(sunrise, sunset, current) }
    }

    @Test
    fun test_remaining_day_light_at_midday() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(12)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = TimeUnit.HOURS.toSeconds(6).toInt()

        assertEquals(expected, sunPosition.remainingDaylight())
    }

    @Test
    fun test_remaining_day_light_at_night() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(22)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = TimeUnit.HOURS.toSeconds(0).toInt()

        assertEquals(expected, sunPosition.remainingDaylight())
    }

    @Test
    fun test_remaining_day_light_at_morning() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(1)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = TimeUnit.HOURS.toSeconds(12).toInt()

        assertEquals(expected, sunPosition.remainingDaylight())
    }

    @Test
    fun test_sun_position_at_midday() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(12)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = 0.5

        assertEquals(expected, sunPosition.sunPosition(), 0.001)
    }

    @Test
    fun test_sun_position_at_night() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(24)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = -0.5

        assertEquals(expected, sunPosition.sunPosition(), 0.001)
    }

    @Test
    fun test_sun_position_at_morning() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(18)
        val current = TimeUnit.HOURS.toSeconds(4)
        val sunPosition = SunPosition(sunrise, sunset, current)

        val expected = -0.8

        assertEquals(expected, sunPosition.sunPosition(), 0.05)
    }
}