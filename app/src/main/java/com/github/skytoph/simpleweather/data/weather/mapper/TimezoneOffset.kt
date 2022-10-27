package com.github.skytoph.simpleweather.data.weather.mapper

interface TimezoneOffset {
    fun withOffset(time: Long): Long

    class Base(private val offset: Int) : TimezoneOffset {
        override fun withOffset(time: Long): Long = time + offset
    }
}

interface TimezoneMapper {
    fun map(timezoneOffset: Int): TimezoneOffset
}

interface ProvidesOffset {
    fun map(mapper: TimezoneMapper): TimezoneOffset
}