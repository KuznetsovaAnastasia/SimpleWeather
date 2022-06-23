package com.github.skytoph.simpleweather.core

import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.TimeUnit

class SunCalculatorTest {

    private val calculator = SunCalculator.Base()

    @Test
    fun test_day_length() {
        val sunrise = 3000L
        val sunset = 6000L
        val dayLength = calculator.duration(sunrise, sunset)

        assertEquals(3000, dayLength)
    }

    @Test
    fun test_day_length_wrong() {
        val sunrise = 6000L
        val sunset = 3000L
        val dayLength = calculator.duration(sunrise, sunset)

        assertEquals(0, dayLength) // todo improve
    }

    @Test
    fun test_remaining_day_light_at_midday() {
        val sunrise = 2000L
        val sunset = 3000L
        val currentTime = 2500L
        val daylight = calculator.remainingDaylight(sunrise, sunset, currentTime)

        assertEquals(500, daylight)
    }

    @Test
    fun test_remaining_day_light_at_night() {
        val sunrise = 2000L
        val sunset = 3000L
        val currentTime = 4000L
        val daylight = calculator.remainingDaylight(sunrise, sunset, currentTime)

        assertEquals(0, daylight)
    }

    @Test
    fun test_remaining_day_light_at_morning() {
        val sunrise = 2000L
        val sunset = 3000L
        val currentTime = 1000L
        val daylight = calculator.remainingDaylight(sunrise, sunset, currentTime)

        assertEquals(1000, daylight)
    }

    @Test
    fun test_remaining_day_light_with_wrong_sunrise() {
        val sunrise = 3000L
        val sunset = 2000L
        val currentTime = 1000L
        val daylight = calculator.remainingDaylight(sunrise, sunset, currentTime)

        assertEquals(0, daylight)
    }

    @Test
    fun test_sun_position_at_midday() {
        val sunrise = 2000L
        val sunset = 3000L
        val currentTime = 2500L
        val sunPosition = calculator.sunPosition(sunrise, sunset, currentTime)

        assertEquals(0.5, sunPosition, 0.001)
    }

    @Test
    fun test_sun_position_at_night() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(20)
        val currentTime = TimeUnit.HOURS.toSeconds(22)
        val sunPosition = calculator.sunPosition(sunrise, sunset, currentTime)

        assertEquals(-0.2, sunPosition, 0.001)
    }

    @Test
    fun test_sun_position_at_morning() {
        val sunrise = TimeUnit.HOURS.toSeconds(6)
        val sunset = TimeUnit.HOURS.toSeconds(20)
        val currentTime = TimeUnit.HOURS.toSeconds(2)
        val sunPosition = calculator.sunPosition(sunrise, sunset, currentTime)

        assertEquals(-0.6, sunPosition, 0.001)
    }
}