package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.util.formatter.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UtilityModule {

    @Binds
    abstract fun tempFormatter(formatter: TemperatureFormatter.Base): TemperatureFormatter

    @Binds
    abstract fun timeFormatter(formatter: TimeFormatter.Base): TimeFormatter

    @Binds
    abstract fun timePattern(formatter: FormatPatterns.Base): FormatPatterns

    @Binds
    abstract fun probabilityFormatter(formatter: ProbabilityFormatter.Base): ProbabilityFormatter

    @Binds
    abstract fun timeFormat(format: TimeFormat.Base): TimeFormat

    @Binds
    abstract fun temperatureFormat(format: TemperatureFormat.Base): TemperatureFormat
}