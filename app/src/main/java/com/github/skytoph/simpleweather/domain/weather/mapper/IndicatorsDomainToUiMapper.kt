package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.core.util.formatter.ProbabilityFormatter
import com.github.skytoph.simpleweather.core.util.formatter.TimeFormatter
import com.github.skytoph.simpleweather.presentation.weather.IndicatorsUi
import javax.inject.Inject
import kotlin.math.roundToInt

interface IndicatorsDomainToUiMapper : Mapper<IndicatorsUi> {
    fun map(
        time: Long,
        uvi: Double,
        precipitationProb: Double,
        airQualityIndex: Int,
    ): IndicatorsUi

    class Base @Inject constructor(
        private val timeFormatter: TimeFormatter,
        private val probabilityFormatter: ProbabilityFormatter,
    ) : IndicatorsDomainToUiMapper {

        override fun map(
            time: Long,
            uvi: Double,
            precipitationProb: Double,
            airQualityIndex: Int,
        ) = IndicatorsUi(
            timeFormatter.timeFull(time),
            uvi.roundToInt().toString(),
            probabilityFormatter.format(precipitationProb),
            airQualityIndex.toString()
        )
    }
}
