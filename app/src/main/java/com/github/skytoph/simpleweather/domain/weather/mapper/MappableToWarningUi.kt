package com.github.skytoph.simpleweather.domain.weather.mapper

import com.github.skytoph.simpleweather.presentation.weather.model.WarningUi

interface MappableToWarningUi{
    fun map(mapper: WarningDomainToUiMapper): WarningUi.Basic
    fun map(mapper: WarningRainDomainToUiMapper): WarningUi.Rain
}