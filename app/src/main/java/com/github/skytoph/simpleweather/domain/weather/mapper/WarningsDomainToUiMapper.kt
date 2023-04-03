package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WarningDomain
import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi
import javax.inject.Inject

interface WarningsDomainToUiMapper : Mapper<List<WarningUi>> {

    fun map(warnings: List<WarningDomain>): List<WarningUi>

    class Base @Inject constructor(
        private val mapper: WarningDomainToUiMapper,
        private val mapperRain: WarningRainDomainToUiMapper
    ) : WarningsDomainToUiMapper {

        override fun map(warnings: List<WarningDomain>): List<WarningUi> = warnings
            .mapIndexed { index, warning ->
                if (index == 0 && warning.containsRain()) warning.map(mapperRain)
                else warning.map(mapper)
            }
            .filter { it.isDescribed() }
            .distinct()
    }
}
