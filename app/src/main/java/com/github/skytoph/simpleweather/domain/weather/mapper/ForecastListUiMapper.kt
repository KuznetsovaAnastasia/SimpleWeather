package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.DailyDomain
import com.github.skytoph.simpleweather.domain.weather.model.HourlyDomain
import com.github.skytoph.simpleweather.domain.weather.model.WarningDomain
import com.github.skytoph.simpleweather.presentation.weather.ListUi
import javax.inject.Inject

interface ForecastListUiMapper : Mapper<ListUi> {
    fun map(
        hourly: List<HourlyDomain>,
        daily: List<DailyDomain>,
        warnings: List<WarningDomain>,
    ): ListUi

    class Base @Inject constructor(
        private val warningsMapper: WarningsDomainToUiMapper,
        private val hourlyMapper: HourlyForecastListToUiMapper,
        private val dailyMapper: DailyForecastListToUiMapper,
    ) : ForecastListUiMapper {

        override fun map(
            hourly: List<HourlyDomain>,
            daily: List<DailyDomain>,
            warnings: List<WarningDomain>,
        ) = ListUi(hourlyMapper.map(hourly), dailyMapper.map(daily), warningsMapper.map(warnings))
    }
}
