package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.core.Mapper
import com.github.skytoph.simpleweather.domain.weather.model.WarningDomain
import com.github.skytoph.simpleweather.presentation.weather.WarningUi
import javax.inject.Inject

interface WarningsDomainToUiMapper : Mapper<List<WarningUi>> {

    fun map(warnings: List<WarningDomain>): List<WarningUi>

    class Base @Inject constructor(private val mapper: WarningDomainToUiMapper) : WarningsDomainToUiMapper {

        override fun map(warnings: List<WarningDomain>): List<WarningUi> =
            warnings.map { it.map(mapper) }.filter { it.isDescribed() }.distinct()

    }

}
