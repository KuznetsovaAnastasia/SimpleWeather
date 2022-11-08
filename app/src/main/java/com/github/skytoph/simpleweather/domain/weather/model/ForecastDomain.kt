package com.github.skytoph.simpleweather.domain.weather.model

import com.github.skytoph.simpleweather.core.Mappable
import com.github.skytoph.simpleweather.domain.weather.mapper.DailyForecastToUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.ForecastListUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.HourlyForecastToUiMapper
import com.github.skytoph.simpleweather.domain.weather.mapper.WarningDomainToUiMapper
import com.github.skytoph.simpleweather.presentation.weather.model.ForecastUi
import com.github.skytoph.simpleweather.presentation.weather.model.ListUi
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi

data class ForecastDomain(
    private val warnings: List<WarningDomain>,
    private val hourly: List<HourlyDomain>,
    private val daily: List<DailyDomain>,
): Mappable<ListUi, ForecastListUiMapper>{

    override fun map(mapper: ForecastListUiMapper): ListUi =
        mapper.map(hourly, daily, warnings)
}

data class WarningDomain(
    private val event: String,
    private val time: Long,
    private val started: Boolean,
    private val precipitationProb: Double,
    private val description: String,
) : Mappable<WarningUi, WarningDomainToUiMapper> {

    override fun map(mapper: WarningDomainToUiMapper): WarningUi =
        mapper.map(event, time, started, precipitationProb, description)
}

data class HourlyDomain(
    private val time: Long,
    private val temp: Double,
    private val weatherId: Int,
    private val precipitationProb: Double,
) : Mappable<ForecastUi.Hourly, HourlyForecastToUiMapper> {

    override fun map(mapper: HourlyForecastToUiMapper): ForecastUi.Hourly =
        mapper.map(time, temp, weatherId, precipitationProb)
}

data class DailyDomain(
    private val time: Long,
    private val temp: Pair<Double, Double>,
    private val weatherId: Int,
    private val precipitationProb: Double,
) : Mappable<ForecastUi.Daily, DailyForecastToUiMapper> {

    override fun map(mapper: DailyForecastToUiMapper): ForecastUi.Daily =
        mapper.map(time, temp, weatherId, precipitationProb)
}