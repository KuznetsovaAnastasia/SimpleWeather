package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormat
import com.github.skytoph.simpleweather.presentation.weather.model.CurrentTimeUi
import javax.inject.Inject

interface TimeUiMapper : Mapper<CurrentTimeUi> {
    fun map(timezone: String): CurrentTimeUi

    class Base @Inject constructor(private val timeFormat: TimeFormat) : TimeUiMapper {
        override fun map(timezone: String) =
            if (timeFormat.is24HourChosen()) CurrentTimeUi.Format24Hours(timezone)
            else CurrentTimeUi.Format12Hours(timezone)
    }
}