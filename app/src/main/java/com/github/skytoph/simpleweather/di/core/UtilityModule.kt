package com.github.skytoph.simpleweather.di.core

import com.github.skytoph.simpleweather.core.util.SunCalculator
import com.github.skytoph.simpleweather.core.util.formatter.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UtilityModule {

    @Binds
    @Singleton
    abstract fun sunCalculator(calculator: SunCalculator.Base): SunCalculator

    @Binds
    @Singleton
    abstract fun tempFormatter(formatter: TemperatureFormatter.Base): TemperatureFormatter

    @Binds
    @Singleton
    abstract fun timeFormatter(formatter: TimeFormatter.Base): TimeFormatter

    @Binds
    @Singleton
    abstract fun probabilityFormatter(formatter: ProbabilityFormatter.Base): ProbabilityFormatter

    @Binds
    abstract fun timeFormat(format: TimeFormat.Base): TimeFormat

    @Binds
    abstract fun temperatureFormat(format: TemperatureFormat.Base): TemperatureFormat
}