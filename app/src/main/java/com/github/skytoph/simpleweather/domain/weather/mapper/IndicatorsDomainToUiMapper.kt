package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.presentation.weather.model.IndicatorsUi
import javax.inject.Inject
import kotlin.math.roundToInt

interface IndicatorsDomainToUiMapper : Mapper<IndicatorsUi> {
    fun map(
        timezone: String,
        uvi: Double,
        precipitationProb: Double,
        airQualityIndex: Int,
    ): IndicatorsUi

    class Base @Inject constructor(
        private val timeMapper: TimeUiMapper,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : IndicatorsDomainToUiMapper {

        override fun map(
            timezone: String,
            uvi: Double,
            precipitationProb: Double,
            airQualityIndex: Int,
        ) = IndicatorsUi(
            timeMapper.map(timezone),
            uvi.roundToInt().toString(),
            probabilityFormatter.format(precipitationProb),
            airQualityIndex.toString()
        )
    }
}
